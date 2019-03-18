package com.wxb.blog.common.base;

import javax.validation.ConstraintViolation;
import java.util.Iterator;
import java.util.Set;

public class JSR303Checker {
    public JSR303Checker() {
    }

    public static void check(Object o) {
        Set<ConstraintViolation<Object>> constraintViolations = BaseValidatorFactory.INSTANCE.getValidator().validate(o, new Class[0]);
        validate(constraintViolations);
    }

    public static void checkWithGroup(Object o, Class... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = BaseValidatorFactory.INSTANCE.getValidator().validate(o, groups);
        validate(constraintViolations);
    }

    private static void validate(Set<ConstraintViolation<Object>> constraintViolations) {
        JSR303CheckException exception = null;
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            exception = new JSR303CheckException();
            Iterator var2 = constraintViolations.iterator();

            while(var2.hasNext()) {
                ConstraintViolation<Object> constraintViolation = (ConstraintViolation)var2.next();
                exception.addError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
        }

        if (exception != null) {
            throw exception;
        }
    }
}
