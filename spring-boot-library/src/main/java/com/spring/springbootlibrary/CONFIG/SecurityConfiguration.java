package com.spring.springbootlibrary.CONFIG;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // Disable Cross site request forgery
      http.csrf().disable();
        // protect endpoints at /api/<type>/secure
       http.authorizeRequests(configurer ->
               configurer.antMatchers(HttpMethod.valueOf("/api/books/secure/**"))
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
}
