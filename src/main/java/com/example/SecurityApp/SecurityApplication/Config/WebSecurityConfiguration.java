package com.example.SecurityApp.SecurityApplication.Config;
import com.example.SecurityApp.SecurityApplication.Filters.JWTAuthFilter;
import com.example.SecurityApp.SecurityApplication.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import static com.example.SecurityApp.SecurityApplication.Entities.enums.Permission.*;
import static com.example.SecurityApp.SecurityApplication.Entities.enums.Role.ADMIN;
import static com.example.SecurityApp.SecurityApplication.Entities.enums.Role.CREATER;

@Configuration
@EnableWebSecurity()
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration {
    private final JWTAuthFilter jwtAuthFilter;
private final OAuth2SuccessHandler oAuth2SuccessHandler;
private static final String[] publicRoutes ={"/error","/auth/**","/home.html"};

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       httpSecurity

               .authorizeHttpRequests(auth->auth
                       .requestMatchers(publicRoutes).permitAll()
                       .requestMatchers(HttpMethod.GET,"/posts/**").permitAll()
                       .requestMatchers(HttpMethod.POST,"/posts/**").hasAnyRole(ADMIN.name(),CREATER.name())
                       .requestMatchers(HttpMethod.POST,"/posts/**").hasAnyAuthority(POST_CREATE.name())
                       .requestMatchers(HttpMethod.PUT,"/posts/**").hasAnyAuthority(POST_UPDATE.name())
                       .requestMatchers(HttpMethod.DELETE,"/posts/**").hasAnyAuthority(POST_DELETE.name())
                       .anyRequest().authenticated())
               .csrf(csrfConfig->csrfConfig.disable()) // this line for csrf token ko disable Karne ke liye hai //
                       .sessionManagement(sessionConfig->sessionConfig
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
               .oauth2Login(oauth2config->oauth2config.failureUrl("/login?error=true")
                       .successHandler(oAuth2SuccessHandler));


////                .formLogin(Customizer.withDefaults());
                       return  httpSecurity.build();
    }

//    @Bean

//    UserDetailsService myInMemoryUserDetailsService(){
//        UserDetails  normalUser = User.withUsername("amit")
//                .password(passwordEncoder().encode("Amit@123"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminUser=User.withUsername("admin")
//                .password(passwordEncoder().encode("Admin@123"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser,adminUser);
//    }

    @Bean
     public  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return   configuration.getAuthenticationManager();
    }

}
