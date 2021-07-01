package br.com.zupacademy.maxley.controller

import br.com.zupacademy.maxley.controller.dto.DetalhesAutor
import br.com.zupacademy.maxley.controller.dto.EnderecoResponse
import br.com.zupacademy.maxley.model.Autor
import br.com.zupacademy.maxley.model.Endereco
import br.com.zupacademy.maxley.repository.AutorRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutorControllerTest{

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var cliente: HttpClient

    lateinit var autor:Autor

    @BeforeEach
    internal fun setup(){
        val enderecoResponse = EnderecoResponse("MG", "Araguari", "Avenida A")
        val endereco = Endereco(enderecoResponse, "123")
        autor = Autor("max", "max@mail.com", "Kotlin é legal", "12345-000", endereco)

        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown(){
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar detalhes de um autor`() {

        val response = cliente.toBlocking().exchange("/autores?email=max@mail.com", DetalhesAutor::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)

//        assertNotNull(response)
//        assertEquals(autor.nome, response.nome)
//        assertEquals(autor.email, response.email)
//        assertEquals(autor.descricao, response.descricao)
    }
}