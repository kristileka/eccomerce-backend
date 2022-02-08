package com.example
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.MediaType
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.net.URI

@RestController
class GreetingController {

    val dataSource = dataSource()
    val connection = dataSource.connection

    @GetMapping("/{name}")

    fun get(@PathVariable name: String) = "Hello, $name"

    @PostMapping(value = ["/add-name"], consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun post(@RequestBody requestBody: String) : String {
        initDb()
        val stmt = connection.createStatement()
        stmt.executeUpdate("INSERT INTO names values('$requestBody')")
        return "Added $requestBody"
    }

    @GetMapping("/everyone")

    fun getAll() : String {
        initDb()
        val stmt = connection.createStatement()
        val rs = stmt.executeQuery("SELECT name FROM names")
        val output = ArrayList<String>()
        while (rs.next()) {
            output.add(rs.getString("name"))
        }
        val names = output.joinToString(", ")
        return "Here are the names: $names"
    }

    internal fun initDb() {
        val stmt = connection.createStatement()
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS names (name text)")
    }

    internal fun dataSource(): HikariDataSource {
        val config = HikariConfig()
        var dbUri = URI(System.getenv("DATABASE_URL") ?: "postgresql://localhost:5432/")
        var dbUserInfo =  dbUri.getUserInfo()
        var username: String?; var password: String?;
        if (dbUserInfo != null) {
            username = dbUserInfo.split(":").get(0)
            password = dbUserInfo.split(":").get(1)
        } else {
            username = System.getenv("DATABASE_USERNAME") ?: null
            password = System.getenv("DATABASE_PASSWORD") ?: null
        }
        if (username != null) {
            config.setUsername(username)
        }
        if (password != null) {
            config.setPassword(password)
        }
        val dbUrl = "jdbc:postgresql://${dbUri.getHost()}:${dbUri.getPort()}${dbUri.getPath()}"
        config.setJdbcUrl(dbUrl)
        return HikariDataSource(config)
    }
}