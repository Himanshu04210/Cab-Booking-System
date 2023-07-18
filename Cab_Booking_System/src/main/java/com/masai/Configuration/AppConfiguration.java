package com.masai.Configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		

        http

            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(cors ->{
                cors.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration cfg= new CorsConfiguration();


                        cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                        cfg.setAllowedMethods(Collections.singletonList("*"));
                        cfg.setAllowCredentials(true);
                        cfg.setAllowedHeaders(Collections.singletonList("*"));
                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
                        return cfg;

                    }
                });
            })

            .authorizeHttpRequests(auth ->{
                auth                        
                        .requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/hello").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/drivers").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/admins/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/drivers", "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/cabBooking").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/users/profile", "/api/wallets/**", "/api/cabBooking/user", "/api/users/update/**").hasRole("USER")
                        .requestMatchers("/api/drivers/profile", "/api/cabBooking/driver").hasRole("DRIVER")
                        .requestMatchers("/api/drivers/email/**", "/api/drivers/id/**", "/api/drivers/location/**", "/api/signIn").hasAnyRole("USER", "DRIVER", "ADMIN")
                        .requestMatchers("/api/**").hasRole("ADMIN")
                        
                        .anyRequest().authenticated();
            })
            .csrf(csrf -> csrf.disable())
            .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
        return http.build();
		
	}
	 
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
