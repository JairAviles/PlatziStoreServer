package server

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.request.receiveText
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.post
import org.jetbrains.ktor.routing.routing

fun Application.main()  {
    install(DefaultHeaders)
    install(CallLogging)

    routing {

        get("/me") {
            val name: String? = call.request.queryParameters["name"]
            val age: String? = call.request.queryParameters["age"]
            val gson: Gson = GsonBuilder().setPrettyPrinting().create()

            if (name != null && age !=null) {
                val ageTemp: Int = try {
                    age.toInt()
                } catch (e: NumberFormatException) {
                    0
                }
                val me: Person = Person(name, ageTemp.toInt())
                val jsonRes = gson.toJson(me)
                call.respondText(jsonRes, ContentType.Application.Json, HttpStatusCode.OK)

            } else {
                val error = Error(HttpStatusCode.BadRequest.value, "Invalid data")
                val jsonRes = gson.toJson(error)
                call.respondText (jsonRes, ContentType.Application.Json, HttpStatusCode.BadRequest)
            }

            call.respondText("Name: $name Edad: $age", ContentType.Text.Html)
        }

        post("/postme") {
            val messagePost = call.receiveText()
            print(messagePost)
            call.respondText("Ok", ContentType.Text.Html, HttpStatusCode.OK)
        }

    }

}