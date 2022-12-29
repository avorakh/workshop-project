package dev.avorakh.workshop.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

public class IpAddressValidator implements ConstraintValidator<IpAddressValidation, String> {

    public static final String IP_V4_ADDRESS_VALIDATION_REGEX = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";
    public static final String IP_V6_ADDRESS_VALIDATION_REGEX = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]" +
        "{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|" +
        "([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]" +
        "{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)" +
        "|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}" +
        "[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9])" +
        "{0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";

    public static final String IP_ADDRESS_VALIDATION_REGEX =
        "(" + IP_V4_ADDRESS_VALIDATION_REGEX + ")|(" + IP_V6_ADDRESS_VALIDATION_REGEX + ")";

    public static final Pattern IP_ADDRESS_VALIDATION_PATTERN = Pattern.compile(IP_ADDRESS_VALIDATION_REGEX);

    public static final String MSG_IP_ADDRESS_MUST_NOT_BE_NULL = "IP address must not be NULL";
    public static final String MSG_IP_ADDRESS_MUST_NOT_BE_EMPTY_OR_BLANK = "IP address must not be empty or blank";

    @Override
    public boolean isValid(@Nullable String value, @NotNull ConstraintValidatorContext context) {

        if (value == null) {
            changeConstraintViolationMessage(context, MSG_IP_ADDRESS_MUST_NOT_BE_NULL);

            return false;
        } else if (value.isBlank()) {
            changeConstraintViolationMessage(context, MSG_IP_ADDRESS_MUST_NOT_BE_EMPTY_OR_BLANK);

            return false;
        }

        return IP_ADDRESS_VALIDATION_PATTERN.matcher(value).matches();
    }

    private void changeConstraintViolationMessage(
        @NotNull ConstraintValidatorContext context,
        @NotNull String messageTemplate
    ) {

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation();
    }
}
