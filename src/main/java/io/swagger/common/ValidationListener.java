package io.swagger.common;

import com.google.inject.spi.ProvisionListener;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atrestman on 2/2/17.
 */
public class ValidationListener implements ProvisionListener {

    private final Logger logger = Logger.getLogger(ValidationListener.class.getName());

    private Validator validator;

    public ValidationListener() {
        this(Validation.buildDefaultValidatorFactory().getValidator());
    }

    public ValidationListener(Validator validator) throws IllegalArgumentException {
        if (validator == null) {
            throw new IllegalArgumentException("The provided validator was null.");
        }
        this.validator = validator;
    }

    @Override
    public <T> void onProvision(ProvisionInvocation<T> provisionInvocation) {
        Object provisioned = provisionInvocation.provision();
        Set<ConstraintViolation<Object>> violations = this.validator.validate(provisioned);
        if (violations != null && violations.size() > 0) {
            for (ConstraintViolation<Object> violation : violations) {
                logger.log(
                        Level.SEVERE,
                        "Constraint violation in {0}: {1}",
                        new Object[]{
                                provisioned.getClass().getName(),
                                violation.getMessage()
                        });
            }
        }
    }

}
