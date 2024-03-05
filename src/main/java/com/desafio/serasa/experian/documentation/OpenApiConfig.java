package com.desafio.serasa.experian.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Unit Test using MockMvc",
                        email = "rayanabonfanti@gmail.com",
                        url = "https://github.com/rayanabonfanti"
                ),
                description = "OpenApi documentation for Unit Test using MockMvc",
                title = "OpenApi specification - Unit Test using MockMvc",
                version = "1.0",
                license = @License(
                        name = "Licence Unit Test",
                        url = "https://github.com/rayanabonfanti"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local TEST",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Local PROD",
                        url = "https://localhost:8443"
                )
        }

)
public class OpenApiConfig {
}
