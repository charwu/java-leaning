package com.wxb.blog.common.base;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public enum BaseValidatorFactory {
    INSTANCE {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        public Validator getValidator() {
            return this.factory.getValidator();
        }
    };

    private BaseValidatorFactory() {
    }

    public abstract Validator getValidator();
}
