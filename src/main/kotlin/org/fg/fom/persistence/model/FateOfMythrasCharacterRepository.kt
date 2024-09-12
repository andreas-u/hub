package org.fg.fom.persistence.model

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.result.UpdateResult
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import arrow.core.Either
import jakarta.enterprise.context.ApplicationScoped
import org.bson.Document
import org.bson.types.ObjectId

interface CharacterRepository {
    fun create(character: FateOfMythrasCharacter): Either<Throwable, FateOfMythrasCharacter>
    fun findById(id: String): Either<Throwable, FateOfMythrasCharacter?>
    fun update(character: FateOfMythrasCharacter): Either<Throwable, FateOfMythrasCharacter>
    fun delete(id: String): Either<Throwable, Boolean>
    fun findAll(): Either<Throwable, List<FateOfMythrasCharacter>>
}

@ApplicationScoped
class FateOfMythrasCharacterRepository(private val mongoClient: MongoClient) : CharacterRepository {
    private val database = mongoClient.getDatabase("fateofmythras")
    private val collection: MongoCollection<Document> = database.getCollection("characters")
    private val objectMapper = ObjectMapper().registerKotlinModule()

    override fun create(character: FateOfMythrasCharacter): Either<Throwable, FateOfMythrasCharacter> = Either.catch {
        val document = Document.parse(objectMapper.writeValueAsString(character))
        val result: InsertOneResult = collection.insertOne(document)
        if (result.wasAcknowledged()) character else throw RuntimeException("Failed to create character")
    }

    override fun findById(id: String): Either<Throwable, FateOfMythrasCharacter?> = Either.catch {
        val document = collection.find(eq("_id", ObjectId(id))).first()
        document?.let { objectMapper.readValue(it.toJson(), FateOfMythrasCharacter::class.java) }
    }

    override fun update(character: FateOfMythrasCharacter): Either<Throwable, FateOfMythrasCharacter> = Either.catch {
        val document = Document.parse(objectMapper.writeValueAsString(character))
        val result: UpdateResult = collection.replaceOne(eq("_id", ObjectId(character.id)), document)
        if (result.wasAcknowledged()) character else throw RuntimeException("Failed to update character")
    }

    override fun delete(id: String): Either<Throwable, Boolean> = Either.catch {
        val result: DeleteResult = collection.deleteOne(eq("_id", ObjectId(id)))
        result.wasAcknowledged() && result.deletedCount > 0
    }

    override fun findAll(): Either<Throwable, List<FateOfMythrasCharacter>> = Either.catch {
        collection.find().map { doc ->
            objectMapper.readValue(doc.toJson(), FateOfMythrasCharacter::class.java)
        }.toList()
    }
}
