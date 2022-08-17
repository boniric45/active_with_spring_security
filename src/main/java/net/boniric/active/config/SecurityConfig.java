package net.boniric.active.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //  Authentification bdd
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,password,true from clients where email=?")
                .authoritiesByUsernameQuery("select email,role from clients where email=?")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        // Form Login config
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/inscription", "/login", "/error").permitAll()
                .antMatchers(HttpMethod.GET, "/authenticated","/console").authenticated()
                .antMatchers(HttpMethod.GET,"/console").hasAuthority("admin")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("email").passwordParameter("password")
                .failureForwardUrl("/error")
                .defaultSuccessUrl("/authenticated")
                .and().logout().deleteCookies("auth_code","JSESSIONID")
                .logoutSuccessUrl("/logout");

        // Logout Config
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "inscription").permitAll();

    }

    //Authorize ressources
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/images/**"); // #3
    }


    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

