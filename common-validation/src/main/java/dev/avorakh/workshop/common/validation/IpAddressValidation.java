package dev.avorakh.workshop.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IpAddressValidator.class)
public @interface IpAddressValidation {
    
    String MSG_INVALID_IP_ADDRESS = "Invalid IP address";

    String message() default MSG_INVALID_IP_ADDRESS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
