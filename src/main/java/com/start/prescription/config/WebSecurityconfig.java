package com.start.prescription.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityconfig {

    // 변경된 AuthenticationManager 빈 생성시 UserDetail, PasswordEncoder 자동 설정되어 불필요 함.
    /*@Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}*/

    // 정적 리소스 security 설정 무시
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**");
    }

    // 인증 (PasswordEncoder, UserDetail 생략 가능)
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 개발용 (필요시 사용)
    /*@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("ROLE_ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    // Spring boot 2.7.0 버전 이상 WebSecurityConfigurerAdapter deprecated
    // filterChain Bean 등록 하여 사용 -> method명은 filterChain으로 사용할것
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
        return http.build();
    }
}
