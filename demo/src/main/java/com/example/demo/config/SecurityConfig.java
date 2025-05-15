package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

import com.example.demo.service.UserAccountsDetailsService;

@Configuration
@EnableMethodSecurity 
public class SecurityConfig {

@Autowired
private UserAccountsDetailsService userAccountsDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
      http.headers(headers -> headers
      .contentSecurityPolicy(csp -> csp.policyDirectives(
      "default-src 'self'; " +
      "script-src 'self' https://cdn.jsdelivr.net https://code.jquery.com; " +
      "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net; " +
      "img-src 'self' data:; " +
      "form-action 'self'; " +
      "frame-ancestors 'none'; " +
      "base-uri 'self'"
))
    .httpStrictTransportSecurity(hsts -> hsts.includeSubDomains(true).maxAgeInSeconds(31536000))
    .referrerPolicy(referrer -> referrer.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER))
);

        return http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/", "/signup/**", "/recipes/**", "/css/**", "/js/**", "/recipe/**"
            , "/api/recipes/**", "/api/ingredients/**").permitAll();
            authorize.requestMatchers("/addRecipe").hasAnyRole("USER", "ADMIN");
            authorize.anyRequest().authenticated();
        }).formLogin(HttpSecurityFormLoginConfigurer -> {
          HttpSecurityFormLoginConfigurer.loginPage("/login").
          defaultSuccessUrl("/").permitAll();
        }).logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll())
        .build();
    }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userAccountsDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

   @Bean
   public PasswordEncoder passwordEncoder(){
        return new Argon2PasswordEncoder(6, 32, 1, 60000, 10);
 }
}
