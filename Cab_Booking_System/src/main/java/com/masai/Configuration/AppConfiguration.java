package com.masai.Configuration;

import jakarta.servlet.http.HttpServletRequest;
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

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		

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
//                            .requestMatchers(HttpMethod.POST,"/driver").permitAll()
//                            .requestMatchers("/hello").permitAll()
                        .requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/drivers").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/admins/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/drivers", "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/hello").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/users/profile").hasRole("USER")
                        .requestMatchers("/api/users/**","/api/drivers/**", "/api/cabBooking", "/api/cabBooking/**", "/api/users", "/api/drivers").hasRole("ADMIN")
                        .requestMatchers("/api/cabBooking/user","/api/cabBooking","/api/wallets/**", "/api/drivers/**").hasRole("USER")
                        .requestMatchers("/api/cabBooking/driver", "/api/drivers/profile").hasRole("DRIVER")
                        .requestMatchers(HttpMethod.PATCH,"/api/users/**","/api/wallets/**").hasRole("USER")
//                     
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
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
