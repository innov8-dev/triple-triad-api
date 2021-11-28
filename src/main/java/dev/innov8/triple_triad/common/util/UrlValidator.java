package dev.innov8.triple_triad.common.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.URL;

public class UrlValidator implements ConstraintValidator<Url, String> {

    @Override
    public boolean isValid(String urlString, ConstraintValidatorContext constraintValidatorContext) {

        try {
            new URL(urlString).openConnection().connect();
        } catch (Throwable t) {
            return false;
        }

        return true;
    }

}
