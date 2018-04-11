package kz.mircella.mircella_electronic_shop.config;

import kz.mircella.mircella_electronic_shop.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/WEB-INF/pages/static/**");
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable();
        http
                .authorizeRequests()
                .antMatchers("/",
                        "/search",
                        "/catalog",
                        "/promotions",
                        "/login",
                        "/sign_up",
                        "/product",
                        "/reviews",
                        "/mail")
                .permitAll();

        http
                .authorizeRequests()
                .antMatchers("/", "/resources/static/**").permitAll();
        http
                .authorizeRequests()
                .antMatchers("/entity-page").permitAll();
        http
                .authorizeRequests()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/denied");

        http
                .authorizeRequests()
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/user-page")
                .failureUrl("/denied")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout").logoutSuccessUrl("/logout-successful");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationProvider);
        auth.userDetailsService(userDetailsService);
    }



}
