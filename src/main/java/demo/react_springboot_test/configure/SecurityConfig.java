package demo.react_springboot_test.configure;

import demo.react_springboot_test.exception.CustomAccessDeniedHandler;
import demo.react_springboot_test.exception.CustomAuthenticationEntryPoint;
import demo.react_springboot_test.jwt.JwtFilter;
import demo.react_springboot_test.jwt.Jwtutil;
import demo.react_springboot_test.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final Jwtutil jwtutil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // 매니저 생성
    }

    @Bean // 함수가 리턴하는 것을 빈으로 등록 -> 재사용 가능하게함
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(
                        request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:3001")); // addAllowedOrigin : 경로 하나만 허용
                            config.addAllowedHeader("*");
                            config.addAllowedMethod("*");
                            config.setAllowCredentials(true);
                            config.addExposedHeader("Authorization");
                            return config;
                        }
                ))
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/", "/login", "/join").permitAll()
                                .requestMatchers("/delete-post").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );

        http.addFilterBefore(new JwtFilter(this.jwtutil), LoginFilter.class);

        http.addFilterAt(new LoginFilter(authenticationManager(this.authenticationConfiguration), this.jwtutil), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(this.customAuthenticationEntryPoint);
            exception.accessDeniedHandler(this.customAccessDeniedHandler);
        });

        return http.build();
    }
}
