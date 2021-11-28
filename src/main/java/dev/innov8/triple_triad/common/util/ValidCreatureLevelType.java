package dev.innov8.triple_triad.common.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreatureLevelTypeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCreatureLevelType {
    String message() default "Level/Type mismatch detected";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
