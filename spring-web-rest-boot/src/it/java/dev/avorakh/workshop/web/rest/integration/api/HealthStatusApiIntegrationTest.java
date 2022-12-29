package dev.avorakh.workshop.web.rest.integration.api;

import dev.avorakh.workshop.web.rest.integration.JupiterSpringIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class HealthStatusApiIntegrationTest extends JupiterSpringIntegrationTest {

    public static final String STATUS_API_URL = "/status";

    @Test
    public void whenStatusApiCall_thenOkResponse() throws Exception {

        mockMvc
            .perform(MockMvcRequestBuilders.get(STATUS_API_URL))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}