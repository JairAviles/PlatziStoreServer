package server

import com.google.gson.GsonBuilder
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.http.HttpMethod
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.route

fun Route.productList() {
    route(HttpMethod.Get, "/list") {
        val products = mutableListOf<Product>()
        (0..20).map {
            products.add(Product((Math.random() * 1000).toInt(),
                    "harrington",
                    "Harrington",
                    "Harrington Jacket",
                    225.00 + it,
                    "http://10.0.3.2:8080/img/harrington.jpg"))

            products.add(Product((Math.random() * 1000).toInt(),
                    "jeans",
                    "Jens",
                    "Levis Jeans 501",
                    175.00 + it,
                    "http://10.0.3.2:8080/img/jeans.jpg"))

            products.add(Product((Math.random() * 1000).toInt(),
                    "monkey_boots",
                    "Monkey Boots",
                    "Monkey Boots",
                    300.00 + it,
                    "http://10.0.3.2:8080/img/monkey_boots.jpg"))

            products.add(Product((Math.random() * 1000).toInt(),
                    "polo",
                    "Polo T-Shirt",
                    "Fred Perry Polo T-Shirt",
                    300.00 + it,
                    "http://10.0.3.2:8080/img/polo.jpg"))
        }

        val payload = ResponseJson(statusCode = HttpStatusCode.OK.value, payload = products)
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonRs = gson.toJson(payload)

        handle {
            call.respondText(jsonRs, ContentType.Application.Json, HttpStatusCode.OK)
        }
    }
}