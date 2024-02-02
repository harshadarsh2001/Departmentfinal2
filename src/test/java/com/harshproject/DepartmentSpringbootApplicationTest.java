package com.harshproject;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springdoc.core.customizers.OpenApiCustomizer;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DepartmentSpringbootApplicationTest {

    @Autowired
    private OpenApiCustomizer openApiCustomizer;

    @Test
    void contextLoads() {
        assertNotNull(openApiCustomizer);
    }

    @Test
    void testOpenApiCustomizer() {
        OpenAPI openApi = getOpenAPIFromCustomizer(openApiCustomizer);

        assertNotNull(openApi);
        assertEquals("Department API", openApi.getInfo().getTitle());
        assertEquals("1.0", openApi.getInfo().getVersion());
        assertEquals("Documentation for Department API", openApi.getInfo().getDescription());

        List<SecurityRequirement> securityRequirements = getSecurityRequirementsFromCustomizer(openApiCustomizer);
        assertNotNull(securityRequirements);
        assertEquals(1, securityRequirements.size());
    }

    // Helper methods for testOpenApiCustomizer
    private OpenAPI getOpenAPIFromCustomizer(OpenApiCustomizer customizer) {
        OpenAPI openAPI = new OpenAPI();
        customizer.customise(openAPI);
        return openAPI;
    }

    private List<SecurityRequirement> getSecurityRequirementsFromCustomizer(OpenApiCustomizer customizer) {
        OpenAPI openAPI = new OpenAPI();
        customizer.customise(openAPI);
        return openAPI.getSecurity();
    }

}

