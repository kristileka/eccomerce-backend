package lunets.co.henez.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import java.util.List
import javax.servlet.http.HttpServletRequest

@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedHeaders = listOf("Authorization", "Cache-Control", "Content-Type")
        corsConfiguration.allowedOrigins = listOf("*")
        corsConfiguration.allowedMethods =
            listOf("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE")
        corsConfiguration.allowCredentials = true
        corsConfiguration.exposedHeaders = listOf("Authorization")

        // You can customize the following part based on your project, it's only a sample
        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest()
            .authenticated().and().csrf().disable().cors()
            .configurationSource { corsConfiguration }
    }
}