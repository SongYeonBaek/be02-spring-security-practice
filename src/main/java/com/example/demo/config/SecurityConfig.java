package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try {
            // antMatchers().permitAll(): login, signup은 모두에게 허용한다
            // antMatchers().hasRole("USER") : USER이라는 Role만 가진 애들에게만 허용한다
            // anyRequest().authenticated() : 이외의 모든 요청은
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/login", "/member/signup").permitAll()
                    .antMatchers("/member/mypage").hasRole("USER")
                    .anyRequest().authenticated();
            // Login form을 활성화
            // /member/login으로 로그인을 처리하고,
            // 성공적으로 로그인되면 /member/mypage로 리다이렉션하라
            http.formLogin()
                    .loginProcessingUrl("/member/login")
                    .defaultSuccessUrl("/member/mypage");

            return http.build();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
