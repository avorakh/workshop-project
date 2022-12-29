package dev.avorakh.workshop.web.rest.integration.api

import dev.avorakh.workshop.web.rest.integration.IntegrationSpecificationWithSpring
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Shared

class HealthStatusApiIntegrationSpecification extends IntegrationSpecificationWithSpring {

    @Shared
    String STATUS_API_URL = '/status'

    def "should successfully check status endpoint"() {

        when:
            def response = mvc.perform(
                MockMvcRequestBuilders.get(STATUS_API_URL)
            )
        then:
            response.andExpect(MockMvcResultMatchers.status().isOk())
    }
}
