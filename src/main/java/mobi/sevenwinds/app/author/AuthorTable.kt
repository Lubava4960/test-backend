package mobi.sevenwinds.app.author

import mobi.sevenwinds.app.budget.BudgetEntity
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

import org.jetbrains.exposed.sql.select

import org.joda.time.DateTime
import kotlin.reflect.KProperty



object AuthorTable : IntIdTable("author") {


    val fullName = varchar("fullName", 256)
    val creationDate = datetime("creation_date").clientDefault { DateTime.now()
    }
}

class QueryType {

}

class AuthorEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AuthorEntity>(AuthorTable)
    var fullName by AuthorTable.fullName
    var creationDate by AuthorTable.creationDate

    fun toRequest(): AuthorRequest {
        return AuthorRequest(fullName)
    }

    fun toResponse(): AuthorResponse {
        return AuthorResponse(fullName, creationDate)
    }

    operator fun getValue(budgetEntity: BudgetEntity, property: KProperty<*>): AuthorEntity? {
        val authorId = budgetEntity.authorId ?: return null
        return AuthorTable.select { AuthorTable.id eq authorId }
            .mapNotNull { AuthorEntity.wrapRow(it) }
            .singleOrNull()
    }


}