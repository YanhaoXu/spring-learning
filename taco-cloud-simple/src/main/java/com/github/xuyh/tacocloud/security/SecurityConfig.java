package com.github.xuyh.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired DataSource dataSource;

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

    //
  }

  @Bean
  public PasswordEncoder encoder() {
    return new StandardPasswordEncoder("53cr3t");
  }
}
