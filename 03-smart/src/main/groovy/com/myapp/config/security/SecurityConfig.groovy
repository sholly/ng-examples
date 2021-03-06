package com.myapp.config.security


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService userDetailsService

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder())
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers('/image/**','/script/**','/style/**')
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//                .antMatchers('/image/**','/script/**','/style/**').permitAll()
                .antMatchers('/').permitAll()

        // admin
                .antMatchers('/admin/**').access("hasRole('ADMIN')")

        // user
                .antMatchers('/user/**').access("hasRole('USER')")

        // anon
                .antMatchers('/**').permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl('/login')
                .loginPage('/login/view')
                .defaultSuccessUrl('/login/redirect')
                .failureUrl('/login/view?status=fail')
                .permitAll()
            .and()
                .logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutUrl('/logout')
                    .logoutSuccessUrl('/')
                    .invalidateHttpSession(true)


        http.headers()
                .httpStrictTransportSecurity()
                .disable()

    }

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers('/image/**','/script/**','/style/**').permitAll()

        // admin
                .antMatchers('/admin/**').access("hasRole('ADMIN')")

        // user
                .antMatchers('/user/**').access("hasRole('USER')")

        // anon
                .antMatchers('/**').permitAll()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin()
                .loginProcessingUrl('/login')
                .loginPage('/login/view')
                .defaultSuccessUrl('/login/redirect')
                .failureUrl('/login/view?status=fail')
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl('/login/sign_out')
                .logoutSuccessUrl('/')
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable()

        http.headers()
                .httpStrictTransportSecurity()
                .disable()

    }
     */

}
