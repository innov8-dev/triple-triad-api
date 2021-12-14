package dev.innov8.triple_triad.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Reflector {

    private Reflector() {
        super();
    }

    public static <T> Optional<Field> findFirstFieldMeetingCondition(Class<T> type, Predicate<Field> condition) {
        return Arrays.stream(type.getFields()).filter(condition).findFirst();
    }

    public static <T> List<Method> getAccessorsAndMutatorsForField(Class<T> type, Field field) {
        return Arrays.stream(type.getMethods()).filter(method -> {
            String pascalCasedFieldName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            String expectedAccessorName = "get" + pascalCasedFieldName;
            String expectedMutatorName = "set" + pascalCasedFieldName;
            return method.getName().equals(expectedAccessorName) || method.getName().equals(expectedMutatorName);
        }).sorted().collect(Collectors.toList());
    }

    public static Object getObjectFieldValue(Object o, Field field) {
        try {
            return field.isAccessible() ? field.get(o) :getAccessorsAndMutatorsForField(o.getClass(), field).get(0).invoke(o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unexpected reflection exception occurred", e);
        }
    }
}
