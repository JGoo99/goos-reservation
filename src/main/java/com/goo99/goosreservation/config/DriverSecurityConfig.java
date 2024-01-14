package com.goo99.goosreservation.config;

import com.goo99.goosreservation.exception.handler.DriverLoginFailureHandler;
import com.goo99.goosreservation.service.impl.DriverDetailsServiceImpl;
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
public class DriverSecurityConfig {

  private final DriverLoginFailureHandler driverLoginFailureHandler;

  @Bean
  public DriverDetailsServiceImpl driverDetailsService() {
    return new DriverDetailsServiceImpl();
  }

  @Bean
  public BCryptPasswordEncoder driverBCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider driverDaoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(driverDetailsService());
    provider.setPasswordEncoder(driverBCryptPasswordEncoder());

    return provider;
  }

  @Bean
  public SecurityFilterChain driverSecurityFilterChain(HttpSecurity http) throws Exception {

    http
      .csrf((c) -> c.disable());

    http
      .securityMatchers((m) -> m.requestMatchers("/driver/**"))
      .authenticationProvider(driverDaoAuthenticationProvider())
      .authorizeHttpRequests((auth) -> auth
        .requestMatchers("/driver/login**", "/", "/driver/join", "/driver/joinProc", "/driver/home").permitAll()
        .requestMatchers("/driver/**").hasRole("DRIVER")
        .anyRequest().permitAll()
      );

    http
      .formLogin((auth) -> auth
        .loginPage("/driver/login")
        .loginProcessingUrl("/driver/loginProc")
        .usernameParameter("email")
        .defaultSuccessUrl("/driver/home")
        .failureHandler(driverLoginFailureHandler)
        .permitAll()
      )
      .logout((auth) -> auth
        .logoutUrl("/driver/logout")
        .logoutSuccessUrl("/")
        .permitAll()
      );

    return http.build();
  }
}
