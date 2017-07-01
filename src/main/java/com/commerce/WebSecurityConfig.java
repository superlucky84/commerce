package com.commerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order; import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by jinwoo on 2017. 6. 22..
 */

/*
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public CustomUserDetailsService userDetailsService() {

        //InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //manager.createUser(User.withUsername("jinwoo").password("dnwlsrla").roles("USER").build());
        //manager.createUser(User.withUsername("admin").password("dnwlsrla").roles("USER","ADMIN").build());
        //return manager;

        return new CustomUserDetailsService();
    }



    //protected void configure(HttpSecurity http) throws Exception {
    //    http.csrf().disable()
    //        .authorizeRequests()
    //        .antMatchers("/resources/**", "/signup", "/about", "/storage/**").permitAll()
    //        .antMatchers("/api/**").hasAuthority("USER")
    //        .antMatchers("/admin/**").hasAuthority("ADMIN")
    //        .anyRequest().authenticated()
    //        .and()
    //        .formLogin();
    //}

    // BASIC
    //protected void configure(HttpSecurity http) throws Exception {
    //    http.antMatcher("/**")
    //        .authorizeRequests()
    //        .anyRequest().hasRole("ADMIN")
    //        .and()
    //        .httpBasic();
    //}

    // FORM
    //protected void configure(HttpSecurity http) throws Exception {
    //    http.csrf().disable()
    //            .authorizeRequests()
    //            .antMatchers("/**").permitAll()
    //            //.anyRequest().permitAll()
    //            //.authenticated()
    //            .and()
    //            .formLogin();
    //}

}
*/


@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public CustomUserDetailsService userDetailsService() {

        //InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //manager.createUser(User.withUsername("jinwoo").password("dnwlsrla").roles("USER").build());
        //manager.createUser(User.withUsername("admin").password("dnwlsrla").roles("USER","ADMIN").build());
        //return manager;

        return new CustomUserDetailsService();
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/api/**")
                .authorizeRequests()
                //.anyRequest().hasAuthority("USER")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
        }
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin();
        }
    }
}
