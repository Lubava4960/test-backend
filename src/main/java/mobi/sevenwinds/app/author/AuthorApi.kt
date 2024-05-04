package mobi.sevenwinds.app.author

import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import org.joda.time.DateTime

fun NormalOpenAPIRoute.budget() {
    route("/author") {
        route("/add").post<Unit, AuthorResponse, AuthorRequest>(info("Добавить автора")) { param, body ->
            respond(AuthorService.add(body))
        }
    }
}

data class AuthorRequest(
    val fullName: String
)
data class AuthorResponse(
    val fullName: String,
    val creationDate: DateTime
)