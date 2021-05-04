package mx.edu.ebc

import io.micronaut.runtime.Micronaut
import groovy.transform.CompileStatic
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*
import io.micronaut.openapi.annotation.OpenAPIInclude
import io.swagger.v3.oas.annotations.security.OAuthFlow
import io.swagger.v3.oas.annotations.security.OAuthFlows
import io.swagger.v3.oas.annotations.security.OAuthScope
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme

@OpenAPIDefinition(
    info = @Info(
            title = "api-mobile-openid",
            version = "0.0"
    )
)
@OpenAPIInclude(
        classes = [io.micronaut.security.endpoints.LoginController.class, io.micronaut.security.endpoints.LogoutController.class] ,
        tags = @Tag(name = "Security")
)
@OpenAPIInclude(
        classes = io.micronaut.management.endpoint.env.EnvironmentEndpoint.class,
        tags = @Tag(name = "Management"),
        security = @SecurityRequirement(name = "BEARER", scopes = ["ADMIN"])
)

@SecurityScheme(name = "roles",
        type = SecuritySchemeType.OAUTH2,
        scheme = "bearer",
        bearerFormat = "jwt",
        flows = @OAuthFlows(
            password = @OAuthFlow(
                        tokenUrl = "http://localhost:8888/auth/realms/master/protocol/openid-connect/token",
                        refreshUrl = ""
                )
        )
)
@CompileStatic
class Application {
    static void main(String[] args) {
        Micronaut.run(Application, args)
    }
}
