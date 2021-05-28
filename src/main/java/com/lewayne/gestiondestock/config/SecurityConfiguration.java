package com.lewayne.gestiondestock.config;

import com.lewayne.gestiondestock.services.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationRequestFilter applicationRequestFilter;

    //@TODO par cobstructeur
    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception { //c'est un manager d'authentification
        authenticationManagerBuilder.userDetailsService(applicationUserDetailsService).passwordEncoder(passwordEncoder());

        //on doit définir un objet qui s'appelle userDetailsService. C'est un servive fourni par spring et nous on doit
                                   // le surcharger ou le créer pour que spring saches ce qu'il dois faire pour récupérer ou authenfier cet utilisateur

      //auth.userDetailsService(applicationUserDetailsService); pour faire l'authentification ou la configuration voici mon service
        // qui va permettre de chercher les informations de l'utilsiateur que ce soit à partir de la base de données ou userMemorie
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // on desactive ceci parceque c'est une application locale
            .authorizeRequests().antMatchers("/**/authenticate",
                "/**/entreprises/create",
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/v3/api-docs/**",
                "/swagger-ui/**")
                .permitAll()  // tout le monde peut acceder à /authenticate
                .anyRequest().authenticated()                                       // mais pour les autres url il faut être authentifié
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                //RESTFULL : par default une API c'est RESFULL c-a-d on ne garde pas le status du client au niveau de notr API

        http.addFilterBefore(applicationRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
       //ce bean est crée poour être injecter dans le controller AuthenticateController
        return super.authenticationManager(); //authenticationManagerBean(); //
    }

    /*  @Bean
    * public AuthenticationManager authenticationManager() throws Exception {
       //ce bean est crée poour être injecter dans le controller AuthenticateController
        return authenticationManagerBean(); //
    }
    * */

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }
}
