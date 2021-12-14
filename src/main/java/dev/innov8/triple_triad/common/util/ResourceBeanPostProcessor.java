package dev.innov8.triple_triad.common.util;

import dev.innov8.triple_triad.common.datasource.EntitySearcher;
import dev.innov8.triple_triad.common.datasource.ResourceRepository;
import dev.innov8.triple_triad.models.Resource;
import dev.innov8.triple_triad.common.services.ResourceService;
import dev.innov8.triple_triad.common.web.ResourceController;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.persistence.EntityManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
@SuppressWarnings({"unchecked"})
public class ResourceBeanPostProcessor implements BeanPostProcessor {

    private final ConfigurableApplicationContext configurableAppContainer;

    @Autowired
    public ResourceBeanPostProcessor(ConfigurableApplicationContext configurableAppContainer) {
        this.configurableAppContainer = configurableAppContainer;
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        // Skip if bean is not annotated with @RestRepository
        if (!bean.getClass().isAnnotationPresent(RestResource.class)) {
            return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
        }

        if (!(bean instanceof Resource)) {
            return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
        }

        Resource resource = (Resource) bean;
        String baseBeanName = bean.getClass().getSimpleName().substring(0, 1).toLowerCase() + bean.getClass().getSimpleName().substring(1) + "s";
        ResourceRepository<? extends Resource> resourceRepo = createAndRegisterResourceRepository(resource.getClass(), baseBeanName);
        ResourceService<? extends Resource> resourceService = createAndRegisterResourceService(resource.getClass(), resourceRepo, baseBeanName);
        createAndRegisterResourceController(bean.getClass(), resourceService, baseBeanName);
        return bean;

    }

    private <T extends Resource> ResourceRepository<T> createAndRegisterResourceRepository(Class<T> resourceType, String baseBeanName) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("Generating ResourceRepository implementation for resource with name: " + baseBeanName);
        EntityManager entityManager = configurableAppContainer.getBean(EntityManager.class);
        ResourceRepository<T> resourceRepo = (ResourceRepository<T>) new ByteBuddy()
                                                .subclass(TypeDescription.Generic.Builder.parameterizedType(ResourceRepository.class, resourceType).build())
                                                .name(baseBeanName + "Repository")
                                                .annotateType(AnnotationDescription.Builder.ofType(Repository.class).build())
                                                .make()
                                                .load(getClass().getClassLoader())
                                                .getLoaded()
                                                .getConstructor(Class.class, EntityManager.class)
                                                .newInstance(resourceType, entityManager);

        configurableAppContainer.getBeanFactory().registerSingleton(baseBeanName + "Repository", resourceRepo);
        return resourceRepo;
    }

    private <T extends Resource> ResourceService<T> createAndRegisterResourceService(Class<? extends Resource> resourceType, ResourceRepository<? extends Resource> resourceRepo, String baseBeanName) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("Generating ResourceService implementation for resource with name: " + baseBeanName);
        EntitySearcher entitySearcher = configurableAppContainer.getBean(EntitySearcher.class);
        PlatformTransactionManager txManager = configurableAppContainer.getBean(PlatformTransactionManager.class);
        ResourceService<T> resourceService = new ByteBuddy()
                                                .subclass(ResourceService.class)
                                                .name(baseBeanName + "Service")
                                                .annotateType(AnnotationDescription.Builder.ofType(Service.class).build())
                                                .make()
                                                .load(getClass().getClassLoader())
                                                .getLoaded()
                                                .getConstructor(ResourceRepository.class, EntitySearcher.class, PlatformTransactionManager.class)
                                                .newInstance(resourceRepo, entitySearcher, txManager);

        configurableAppContainer.getBeanFactory().registerSingleton(baseBeanName + "Service", resourceService);
        return resourceService;
    }

    private <T extends Resource> void createAndRegisterResourceController(Class<?> resourceType, ResourceService<? extends Resource> resourceService, String baseBeanName) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("Generating ResourceController implementation for resource with name: " + baseBeanName);
        ResourceController<T> resourceController = new ByteBuddy()
                                                        .subclass(ResourceController.class)
                                                        .name(baseBeanName + "Controller")
                                                        .annotateType(AnnotationDescription.Builder.ofType(RestController.class).build())
                                                        .annotateType(AnnotationDescription.Builder.ofType(RequestMapping.class).defineArray("value", baseBeanName).build())
                                                        .make()
                                                        .load(getClass().getClassLoader())
                                                        .getLoaded()
                                                        .getConstructor(ResourceService.class)
                                                        .newInstance(resourceService);

        configurableAppContainer.getBeanFactory().registerSingleton(baseBeanName + "Controller", resourceController);

    }

}
