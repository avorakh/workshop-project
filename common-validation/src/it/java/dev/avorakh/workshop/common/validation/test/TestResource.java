package dev.avorakh.workshop.common.validation.test;

import dev.avorakh.workshop.common.validation.IpAddressValidation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
public class TestResource {

    @IpAddressValidation
    @NotNull String ipAddress;
}
