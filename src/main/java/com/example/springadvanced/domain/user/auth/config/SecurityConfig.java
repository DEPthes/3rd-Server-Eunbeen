package com.example.springadvanced.domain.user.auth.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll() // "/public/**" 경로에 대한 접근은 인증 없이 허용
                .anyRequest().authenticated() // 그 외의 모든 요청은 인증이 필요
                .and()
                .formLogin(); // 기본 로그인 폼 제공
    }
}