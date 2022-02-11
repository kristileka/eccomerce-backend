package  lunets.co.henez.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {


    override fun configure(httpSecurity: HttpSecurity) {
        val corsConfiguration = CorsConfiguration();
        corsConfiguration.allowedHeaders = listOf(
            "Origin",
            "X-Requested-With",
            "Accept",
            "Access-Control-Expose-Headers",
            "Authorization",
            "Content-Type",
            "Access-Control-Allow-Headers",
            "Access-Control-Allow-Credentials",
            "Access-Control-Allow-Origin"
        );
        corsConfiguration.allowedOriginPatterns = listOf("*")
        corsConfiguration.allowedMethods = listOf(
            "GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"
        )
        httpSecurity.cors().configurationSource { request -> corsConfiguration }.and().csrf().disable()
            .authorizeRequests().antMatchers("/**").permitAll()
    }
}
