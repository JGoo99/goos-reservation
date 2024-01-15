package com.goo99.goosreservation.config;

import com.goo99.goosreservation.exception.handler.OwnerLoginFailureHandler;
import com.goo99.goosreservation.service.impl.OwnerDetailsServiceImpl;
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
@Order(0)
@RequiredArgsConstructor
public class OwnerSecurityConfig {

  private final OwnerLoginFailureHandler ownerLoginFailureHandler;

  @Bean
  public OwnerDetailsServiceImpl ownerDetailsService() {
    return new OwnerDetailsServiceImpl();
  }

  @Bean
  public BCryptPasswordEncoder ownerBCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider ownerDaoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(ownerDetailsService());
    provider.setPasswordEncoder(ownerBCryptPasswordEncoder());

    return provider;
  }

  @Bean
  public SecurityFilterChain ownerSecurityFilterChain(HttpSecurity http) throws Exception {

    http
      .csrf((c) -> c.disable());

    http
      .securityMatchers((m) -> m.requestMatchers("/owner/**"))
      .authenticationProvider(ownerDaoAuthenticationProvider())
      .authorizeHttpRequests((auth) -> auth
        .requestMatchers("/owner/login**", "/", "/owner/join", "/owner/joinProc", "/owner/home").permitAll()
        .requestMatchers("/owner/**").hasRole("OWNER")
        .anyRequest().permitAll()
      );

    http
      .formLogin((auth) -> auth
        .loginPage("/owner/login")
        .loginProcessingUrl("/owner/loginProc")
        .usernameParameter("email")
        .defaultSuccessUrl("/owner/home")
        .failureHandler(ownerLoginFailureHandler)
        .permitAll()
      )
      .logout((auth) -> auth
        .logoutUrl("/owner/logout")
        .logoutSuccessUrl("/")
        .permitAll()
      );

    return http.build();
  }
}
