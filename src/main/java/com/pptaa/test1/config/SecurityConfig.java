package com.pptaa.test1.config;

import com.pptaa.test1.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    // private MemberService memberService; 

    //WebSecurity는 FilterChainProxy를 생성하는 필터
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/**");
    }
    //Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
    // HttpSecurity를 통해 HTTP 요청에 대한 웹 기반 보안을 구성할 수 있음
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //         .antMatchers("/myPage").hasRole("MEMBER")
    //         .antMatchers("/**").permitAll()
    //     .and()
    //         .formLogin()
    //         .loginPage("/selectform")
    //         .defaultSuccessUrl("/")
    //         .permitAll()
    //     .and()
    //         .logout()
    //         .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
    //         .logoutSuccessUrl("/user/logout/result")
    //         .invalidateHttpSession(true)
    //     .and()
    //         .exceptionHandling().accessDeniedPage("/user/denied");
    // }
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     // TODO Auto-generated method stub
    //     super.configure(auth);
    //     auth.userDetailsService().passwordEncoder(passwordEncoder());
    // }
}