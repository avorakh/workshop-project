package dev.avorakh.workshop.common.validation

import dev.avorakh.workshop.common.validation.test.TestResource
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import spock.lang.Shared
import spock.lang.Specification

import static org.hamcrest.Matchers.hasSize 

class IpAddressResourceValidationTest extends Specification {

    @Shared
    String FLD_IP_ADDRESS = 'ipAddress'

    @Shared
    ValidatorFactory factory
    @Shared
    Validator validator

    def setup() {
        Locale.setDefault(Locale.ENGLISH)
        factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    def 'should successfully create IP address resource if #ipAddress is valid'(String ipAddress) {

        given:
            def resource = new TestResource(ipAddress: ipAddress)
        when:
            def violations = validator.validate(resource)
        then:
            violations.empty
        where:
            ipAddress << ['29.247.131.68', '2a0a:7d80:8::']
    }

    def 'should thrown exception by  IP address resource creation if ip address is null'() {

        given:
            def resource = new TestResource()
        when:
            def violations = validator.validate(resource)
        then:
            !violations.empty
            violations hasSize(1)
            violations.every {
                it.message == IpAddressValidator.MSG_IP_ADDRESS_MUST_NOT_BE_NULL
                it.propertyPath.toString() == FLD_IP_ADDRESS
            }
    }

    def 'should thrown exception by  IP address resource creation if ip address is empty or blank'(String ipAddress) {

        given:
            def resource = new TestResource(ipAddress: ipAddress)
        when:
            Set<ConstraintViolation<TestResource>> violations = validator.validate(resource)
        then:
            !violations.empty
            violations hasSize(1)
            violations.every {
                it.message == IpAddressValidator.MSG_IP_ADDRESS_MUST_NOT_BE_EMPTY_OR_BLANK
                it.propertyPath.toString() == FLD_IP_ADDRESS
            }
        where:
            ipAddress << ['', '   ']
    }

    def 'should thrown exception by  IP address resource creation if ip address is invalid'(String invalidIpAddress) {

        given:
            def resource = new TestResource(ipAddress: invalidIpAddress)
        when:
            def violations = validator.validate(resource)
        then:
            !violations.empty
            violations hasSize(1)
            violations.every {
                it.message == IpAddressValidation.MSG_INVALID_IP_ADDRESS
                it.propertyPath.toString() == FLD_IP_ADDRESS
            }
        where:
            invalidIpAddress << ['429.247.131.68', '2N0a:7d80:8::']
    }
}