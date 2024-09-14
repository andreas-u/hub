package org.fg.fom.domain

import arrow.core.Either
import jakarta.enterprise.context.ApplicationScoped
import org.fg.fom.persistence.model.FateOfMythrasCharacter

// FateOfMythrasCharacterService.kt

interface CharacterService<T> {
    fun createCharacter(character: T): Either<Throwable, T>
    fun getCharacter(id: String): Either<Throwable, T?>
    fun getAllCharacters(): Either<Throwable, List<T>>
    fun updateCharacter(character: T): Either<Throwable, T>
    fun deleteCharacter(id: String): Either<Throwable, Boolean>
}

@ApplicationScoped
class FateOfMythrasCharacterService(private val repository: FateOfMythrasCharacterRepository) :
    CharacterService<FateOfMythrasCharacter> {

    override fun createCharacter(character: FateOfMythrasCharacter): Either<Throwable, FateOfMythrasCharacter> {
        return repository.create(character)
    }

    override fun getCharacter(id: String): Either<Throwable, FateOfMythrasCharacter?> {
        return repository.findById(id)
    }

    override fun getAllCharacters(): Either<Throwable, List<FateOfMythrasCharacter>> {
        return repository.findAll()
    }

    override fun updateCharacter(character: FateOfMythrasCharacter): Either<Throwable, FateOfMythrasCharacter> {
        return repository.update(character)
    }

    override fun deleteCharacter(id: String): Either<Throwable, Boolean> {
        return repository.delete(id)
    }
}
