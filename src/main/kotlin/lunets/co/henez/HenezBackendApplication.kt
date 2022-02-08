package lunets.co.henez

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


@SpringBootApplication
class HenezBackendApplication

@RequestMapping("/")
@ResponseBody
fun home(): String? {
    return "Hello World!"
}


fun main(args: Array<String>) {
    runApplication<HenezBackendApplication>(*args)
}