package com.spring.springbootlibrary.CONFIG;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // Disable Cross site request forgery
        http.csrf().disable();
        // protect endpoints at /api/<type>/secure
        http.authorizeRequests(configurer ->
                        configurer.antMatchers("/api/books/secure/**")
                                .authenticated())
                .oauth2ResourceServer()
                .jwt();
        //add cors filters
        http.cors();

        //add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        //Force nonempty response body for 401 s to make response friendly
        Okta.configureResourceServer401ResponseBody(http);


        return http.build();
    }

    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        var jwtDecoder = NimbusJwtDecoder
                .withJwkSetUri(properties.getJwt().getJwkSetUri())
                .build();
        // additional configuration...
        return jwtDecoder;
    }



}