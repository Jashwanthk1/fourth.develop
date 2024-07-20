package com.office.fourth.develop.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SwaggerConfig {

	private final Environment env;

	private final BuildInfoProvider buildInfoProvider;

	@Bean
	public OpenAPI myOpenAPI(HttpServletRequest request) {
		Server server = new Server();
		server.setUrl(env.getProperty("swagger.server.url"));
		server.setDescription("environment (dev/stg)");
		Info info = new Info().title("fourth develop").version("1.0").termsOfService(env.getProperty("swagger.log.url"))
				.description(buildInfoProvider.getBuildTimestamp("Asia/Kolkata"));
		return new OpenAPI().info(info).servers(List.of(server))
				.addSecurityItem(new SecurityRequirement().addList("Fourth.evelop"))
				.components(new Components().addSecuritySchemes("Fourth.evelop", new SecurityScheme()
						.name("Fourth.evelop").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}

}
