package lideraamerica.pruebabackend.security.config;

import lideraamerica.pruebabackend.security.jwt.JwtEntryPoint;
import lideraamerica.pruebabackend.security.jwt.JwtTokenFilter;
import lideraamerica.pruebabackend.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainAuthSecurityConfig {

  @Autowired
  UsersDetailServiceImpl userDetailsServiceImpl;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtEntryPoint jwtEntryPoint;

  @Autowired
  JwtTokenFilter jwtFilter;

  AuthenticationManager authenticationManager;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
    builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    authenticationManager = builder.build();
    http.authenticationManager(authenticationManager);
    http.cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/auth/**","/h2-console/**")
        .permitAll()
        .anyRequest().authenticated()
        .and().exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().headers().frameOptions().disable();
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

}
