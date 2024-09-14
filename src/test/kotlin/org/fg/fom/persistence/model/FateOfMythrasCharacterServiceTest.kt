package org.fg.fom.persistence.model

import arrow.core.Either
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.fg.fom.domain.FateOfMythrasCharacterService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FateOfMythrasCharacterServiceTest {

    private lateinit var repository: FateOfMythrasCharacterRepository
    private lateinit var service: FateOfMythrasCharacterService

    @BeforeEach
    fun setup() {
        repository = mockk()
        service = FateOfMythrasCharacterService(repository)
    }

    @Test
    fun `createCharacter should return Right when successful`() {
        val character = FateOfMythrasCharacter("Test Character", fatePoints = 3)
        every { repository.create(character) } returns Either.Right(character)

        val result = service.createCharacter(character)

        result.shouldBeTypeOf<Either.Right<FateOfMythrasCharacter>>()
        result.value shouldBe character
        verify(exactly = 1) { repository.create(character) }
    }

    @Test
    fun `getCharacter should return Right with character when found`() {
        val id = "test-id"
        val character = FateOfMythrasCharacter("Test Character", fatePoints = 3)
        every { repository.findById(id) } returns Either.Right(character)

        val result = service.getCharacter(id)

        result.shouldBeTypeOf<Either.Right<FateOfMythrasCharacter?>>()
        result.value shouldBe character
        verify(exactly = 1) { repository.findById(id) }
    }

    @Test
    fun `getAllCharacters should return Right with list of characters`() {
        val characters = listOf(
            FateOfMythrasCharacter("Character 1", fatePoints = 3),
            FateOfMythrasCharacter("Character 2", fatePoints = 4)
        )
        every { repository.findAll() } returns Either.Right(characters)

        val result = service.getAllCharacters()

        result.shouldBeTypeOf<Either.Right<List<FateOfMythrasCharacter>>>()
        result.value shouldBe characters
        verify(exactly = 1) { repository.findAll() }
    }

    @Test
    fun `updateCharacter should return Right when successful`() {
        val character = FateOfMythrasCharacter("Updated Character", fatePoints = 5)
        every { repository.update(character) } returns Either.Right(character)

        val result = service.updateCharacter(character)

        result.shouldBeTypeOf<Either.Right<FateOfMythrasCharacter>>()
        result.value shouldBe character
        verify(exactly = 1) { repository.update(character) }
    }

    @Test
    fun `deleteCharacter should return Right with true when successful`() {
        val id = "test-id"
        every { repository.delete(id) } returns Either.Right(true)

        val result = service.deleteCharacter(id)

        result.shouldBeTypeOf<Either.Right<Boolean>>()
        result.value shouldBe true
        verify(exactly = 1) { repository.delete(id) }
    }

    @Test
    fun `operations should return Left when repository throws an exception`() {
        val exception = RuntimeException("Test exception")
        every { repository.create(any()) } returns Either.Left(exception)
        every { repository.findById(any()) } returns Either.Left(exception)
        every { repository.findAll() } returns Either.Left(exception)
        every { repository.update(any()) } returns Either.Left(exception)
        every { repository.delete(any()) } returns Either.Left(exception)

        val character = FateOfMythrasCharacter("Test Character", fatePoints = 3)

        service.createCharacter(character).shouldBeTypeOf<Either.Left<Throwable>>()
        service.getCharacter("test-id").shouldBeTypeOf<Either.Left<Throwable>>()
        service.getAllCharacters().shouldBeTypeOf<Either.Left<Throwable>>()
        service.updateCharacter(character).shouldBeTypeOf<Either.Left<Throwable>>()
        service.deleteCharacter("test-id").shouldBeTypeOf<Either.Left<Throwable>>()
    }
}
