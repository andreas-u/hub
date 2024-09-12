package org.fg.fom.persistence.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/characters/fom")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterForReflection
class FateOfMythrasCharacterController(private val service: FateOfMythrasCharacterService) {

    @POST
    fun createCharacter(character: FateOfMythrasCharacter): Response {
        return service.createCharacter(character).fold(
            { error -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.message).build() },
            { createdCharacter -> Response.status(Response.Status.CREATED).entity(createdCharacter).build() }
        )
    }

    @GET
    @Path("/{id}")
    fun getCharacter(@PathParam("id") id: String): Response {
        return service.getCharacter(id).fold(
            { error -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.message).build() },
            { character ->
                character?.let { Response.ok(it).build() }
                    ?: Response.status(Response.Status.NOT_FOUND).build()
            }
        )
    }

    @GET
    fun getAllCharacters(): Response {
        return service.getAllCharacters().fold(
            { error -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.message).build() },
            { characters -> Response.ok(characters).build() }
        )
    }

    @PUT
    fun updateCharacter(character: FateOfMythrasCharacter): Response {
        return service.updateCharacter(character).fold(
            { error -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.message).build() },
            { updatedCharacter -> Response.ok(updatedCharacter).build() }
        )
    }

    @DELETE
    @Path("/{id}")
    fun deleteCharacter(@PathParam("id") id: String): Response {
        return service.deleteCharacter(id).fold(
            { error -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.message).build() },
            { deleted ->
                if (deleted) Response.noContent().build()
                else Response.status(Response.Status.NOT_FOUND).build()
            }
        )
    }
}
