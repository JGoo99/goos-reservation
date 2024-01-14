package com.goo99.goosreservation.config;

import com.goo99.goosreservation.exception.handler.UserLoginFailureHandler;
import com.goo99.goosreservation.service.impl.UserCustomDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserSecurityConfig {

  private final UserLoginFailureHandler userLoginFailureHandler;

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
      .securityMatchers((m) -> m.requestMatchers("/**"))
      .authenticationProvider(userDaoAuthenticationProvider())
      .authorizeHttpRequests((auth) -> auth
        .requestMatchers("/user/home").permitAll()
        .requestMatchers("/user/**", "/taxi/**").hasRole("USER")
        .anyRequest().permitAll()
      );

    http
      .formLogin((auth) -> auth
        .loginPage("/login")
        .loginProcessingUrl("/loginProc")
        .usernameParameter("email")
        .defaultSuccessUrl("/home")
        .failureHandler(userLoginFailureHandler)
        .permitAll()
      )
      .logout((auth) -> auth
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .permitAll()
      );

    return http.build();
  }
}
