package com.github.xuyh.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private DataSource dataSource;
  @Autowired private UserDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 基于内存的用户存储
    //    auth.inMemoryAuthentication()
    //        .withUser("saber")
    //        .password("{noop}1234abcd")
    //        .authorities("ROLE_USER")
    //        .and()
    //        .withUser("archer")
    //        .password("2234abcd")
    //        .authorities("ROLE_USER");

    // 基于jdbc的用户存储
    //    auth.jdbcAuthentication()
    //        .dataSource(dataSource)
    //        .usersByUsernameQuery("select username, password, enabled from User " + "where
    // username=?")
    //        .authoritiesByUsernameQuery(
    //            "select username, authority from UserAuthorities " + "where username=?")
    //        .passwordEncoder(encoder());

    // 自定义用户认证
    auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/design", "/order")
        .hasRole("USER")
        .antMatchers("/", "/**")
        .permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        //        .usernameParameter("username")
        //        .passwordParameter("password")
        //        .defaultSuccessUrl("/design", true) // true 强制定向页面
        .and()
        .logout()
        .logoutSuccessUrl("/");
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}
