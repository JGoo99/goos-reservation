package com.goo99.goosreservation.config;

import com.goo99.goosreservation.service.impl.UserCustomDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(1)
public class UserSecurityConfig {

  @Bean
  public UserCustomDetailsServiceImpl userCustomDetailsService() {
    return new UserCustomDetailsServiceImpl();
  }

  @Bean
  public BCryptPasswordEncoder userBCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider userDaoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userCustomDetailsService());
    provider.setPasswordEncoder(userBCryptPasswordEncoder());

    return provider;
  }

  @Bean
  public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {

    http
      .csrf((c) -> c.disable());

    http
      .authenticationProvider(userDaoAuthenticationProvider())
      .securityMatchers((m) -> m.requestMatchers("/**"))
      .authorizeHttpRequests((auth) -> auth
        .requestMatchers("/").permitAll()
        .requestMatchers("/user/**").hasRole("USER")
        .anyRequest().permitAll()
      );

    http
      .formLogin((auth) -> auth
        .loginPage("/login")
        .loginProcessingUrl("/loginProc")
        .usernameParameter("email")
        .defaultSuccessUrl("/home")
      )
      .logout((auth) -> auth
        .logoutUrl("/logout")
        .logoutSuccessUrl("/"));

    return http.build();
  }
}
