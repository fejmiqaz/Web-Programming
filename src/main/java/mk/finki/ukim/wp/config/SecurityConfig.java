package mk.finki.ukim.wp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/books", "/books/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/add", "/books/add", "/edit/**", "/books/edit/**", "/delete/**", "/books/delete/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).formLogin(form -> form
                        .defaultSuccessUrl("/books", true)
                        .permitAll()
                ).logout(logout -> logout
                        .logoutSuccessUrl("/books")
                        .permitAll()
                ).csrf(Customizer.withDefaults());
        return http.build();
    }

}
