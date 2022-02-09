package lunets.co.henez

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EnableJpaRepositories
class HenezBackendApplication
fun main(args: Array<String>) {
    runApplication<HenezBackendApplication>(*args)
}