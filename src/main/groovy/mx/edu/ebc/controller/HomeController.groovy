package mx.edu.ebc.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.oauth2.endpoint.token.response.OauthUserDetailsMapper
import io.micronaut.security.rules.SecurityRule
import io.reactivex.Single
import io.micronaut.http.MediaType
import javax.validation.constraints.NotBlank
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement

@Controller("/")
class HomeController {

    @Get(uri="/hola")
    HttpResponse index() {
    URI location = new URI("/swagger-ui")
    return HttpResponse.redirect(location)
  }
    @Get(uri="/greeting/{name}", produces= MediaType.TEXT_PLAIN)
    @Operation(summary = "Greets a person",
            description = "A friendly greeting is returned"
    )
    @ApiResponse(
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(type="string"))
    )
    @ApiResponse(responseCode = "400", description = "Invalid Name Supplied")
    @ApiResponse(responseCode = "404", description = "Person not found")
    @Tag(name = "greeting")
    Single<String> greetings(@Parameter(description="The name of the person") @NotBlank String name) {
        return Single.just("Hello $name, How are you doing?")
    }

    @Get("/views")
    String view(@Header("Authorization") String authorization) {
      println(authorization.dump())
        
        return "You are viewer!";
    }

    @Get("/admins")
    @Produces
    String admin() {
        return "{admin:true}";
    }
}