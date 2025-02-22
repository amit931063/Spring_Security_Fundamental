package com.example.SecurityApp.SecurityApplication.Config;
import com.example.SecurityApp.SecurityApplication.Filters.JWTAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class WebSecurityConfiguration {
    private final JWTAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       httpSecurity

               .authorizeHttpRequests(auth->auth
                       .requestMatchers("/posts","/error","/auth/**").permitAll()
//                       .requestMatchers("/posts/**").hasAnyRole("ADMIN")
                       .anyRequest().authenticated())
               .csrf(csrfConfig->csrfConfig.disable()) // this line for csrf token ko disable karne ke liye hai //
                       .sessionManagement(sessionConfig->sessionConfig
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

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
