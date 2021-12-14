package dev.innov8.triple_triad.common.util;

import dev.innov8.triple_triad.common.datasource.EntitySearcher;
import dev.innov8.triple_triad.common.datasource.ResourceRepository;
import dev.innov8.triple_triad.common.models.Resource;
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
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.persistence.EntityManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

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

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>|<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        Resource resource = (Resource) bean;
        String baseBeanName = bean.getClass().getSimpleName().substring(0, 1).toLowerCase() + bean.getClass().getSimpleName().substring(1) + "s";
        System.out.println(baseBeanName);
        ResourceRepository<? extends Resource> resourceRepo = createAndRegisterResourceRepository(resource.getClass(), baseBeanName);
        ResourceService<? extends Resource> resourceService = createAndRegisterResourceService(resource.getClass(), resourceRepo, baseBeanName);
        createAndRegisterResourceController(bean.getClass(), resourceService, baseBeanName);
        return bean;

    }

    private <T extends Resource> ResourceRepository<T> createAndRegisterResourceRepository(Class<T> resourceType, String baseBeanName) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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
        EntitySearcher entitySearcher = configurableAppContainer.getBean("entitySearcher", EntitySearcher.class);
        ResourceService<T> resourceService = new ByteBuddy()
                                                .subclass(ResourceService.class)
                                                .name(baseBeanName + "Service")
                                                .annotateType(AnnotationDescription.Builder.ofType(Service.class).build())
                                                .make()
                                                .load(getClass().getClassLoader())
                                                .getLoaded()
                                                .getConstructor(CrudRepository.class, EntitySearcher.class)
                                                .newInstance(resourceRepo, entitySearcher);

        configurableAppContainer.getBeanFactory().registerSingleton(baseBeanName + "Service", resourceService);
        return resourceService;
    }

    private <T extends Resource> void createAndRegisterResourceController(Class<?> resourceType, ResourceService<? extends Resource> resourceService, String baseBeanName) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

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

        RequestMappingHandlerMapping handlerMapping = configurableAppContainer.getBean(RequestMappingHandlerMapping.class);

        for (Method controllerMethod : resourceController.getClass().getDeclaredMethods()) {

            RequestMappingInfo requestMappingInfo = null;

            if (controllerMethod.isAnnotationPresent(GetMapping.class)) {
                GetMapping anno = controllerMethod.getAnnotation(GetMapping.class);
                requestMappingInfo = mapAnnotationToRequestMappingInfo(anno);
            } else if (controllerMethod.isAnnotationPresent(PostMapping.class)) {
                PostMapping anno = controllerMethod.getAnnotation(PostMapping.class);
                requestMappingInfo = mapAnnotationToRequestMappingInfo(anno);
            } else if (controllerMethod.isAnnotationPresent(PatchMapping.class)) {
                PatchMapping anno = controllerMethod.getAnnotation(PatchMapping.class);
                requestMappingInfo = mapAnnotationToRequestMappingInfo(anno);
            } else if (controllerMethod.isAnnotationPresent(DeleteMapping.class)) {
                DeleteMapping anno = controllerMethod.getAnnotation(DeleteMapping.class);
                requestMappingInfo = mapAnnotationToRequestMappingInfo(anno);
            }

            handlerMapping.registerMapping(requestMappingInfo, resourceController, controllerMethod);

        }

    }

    private RequestMappingInfo mapAnnotationToRequestMappingInfo(Annotation annotation) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (annotation instanceof GetMapping) {
            GetMapping anno = (GetMapping) annotation;
            return RequestMappingInfo.paths(anno.value())
                                     .methods(RequestMethod.GET)
                                     .produces(anno.produces())
                                     .consumes(anno.consumes())
                                     .headers(anno.headers())
                                     .params(anno.params())
                                     .build();
        } else if (annotation instanceof PostMapping) {
            PostMapping anno = (PostMapping) annotation;
            return RequestMappingInfo.paths(anno.value())
                                     .methods(RequestMethod.POST)
                                     .produces(anno.produces())
                                     .consumes(anno.consumes())
                                     .headers(anno.headers())
                                     .params(anno.params())
                                     .build();
        } else if (annotation instanceof PatchMapping) {
            PatchMapping anno = (PatchMapping) annotation;
            return RequestMappingInfo.paths(anno.value())
                                     .methods(RequestMethod.PATCH)
                                     .produces(anno.produces())
                                     .consumes(anno.consumes())
                                     .headers(anno.headers())
                                     .params(anno.params())
                                     .build();
        } else if (annotation instanceof DeleteMapping) {
            DeleteMapping anno = (DeleteMapping) annotation;
            return RequestMappingInfo.paths(anno.value())
                                     .methods(RequestMethod.DELETE)
                                     .produces(anno.produces())
                                     .consumes(anno.consumes())
                                     .headers(anno.headers())
                                     .params(anno.params())
                                     .build();
        } else {
            throw new RuntimeException("Unexpected annotation provided.");
        }

    }

}
