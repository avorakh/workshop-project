package dev.avorakh.workshop.web.rest.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@WebMvcTest
@AutoConfigureMockMvc
class IntegrationSpecificationWithSpring extends Specification {

    @Autowired
    MockMvc mvc
}
