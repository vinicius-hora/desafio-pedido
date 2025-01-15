package com.desafio.pedidos.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${config.openapi.dev-url}")
    private String devUrl;

    @Value("${config.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        //authorization and security token
//        final String security = "Constantes.AUTHORIZATION";
//        SecurityRequirement securityRequirement = new SecurityRequirement();
//        securityRequirement.addList(security);

//        Components components = new Components();
//        components.addSecuritySchemes(security, new SecurityScheme()
//                .name(security)
//                .type(SecurityScheme.Type.APIKEY)
//                .in(SecurityScheme.In.HEADER)
//                .scheme(security));

        //servers
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        //contact
        Contact contact = new Contact();
        contact.setEmail("vinicius_hora@live.com");
        contact.setName("Vinicius");
        contact.setUrl("https://www.linkedin.com/in/vinicius-bastos-208ab7189/");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
        //info
        Info info = new Info()
                .title("Desafio pedidos BTG")
                .version("1.0")
                .contact(contact)
                .description("Backend da api do desafio BTG").termsOfService("https://www.bezkoder.com/terms")
                .license(mitLicense);
//        passagem dos parametros de configuração
        return new OpenAPI()
//                .addSecurityItem(securityRequirement)
//                .components(new Components())
                .info(info).servers(List.of(devServer, prodServer));
    }
}
