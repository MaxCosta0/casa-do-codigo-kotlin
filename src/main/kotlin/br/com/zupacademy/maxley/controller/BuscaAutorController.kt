package br.com.zupacademy.maxley.controller

import br.com.zupacademy.maxley.controller.dto.DetalhesAutor
import br.com.zupacademy.maxley.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.transaction.Transactional

@Controller("/autores")
class BuscaAutorController(val autorRepository: AutorRepository) {

    @Get
    @Transactional
    fun busca(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank()) {
            return HttpResponse.ok(
                autorRepository.findAll()
                    .map { autor -> DetalhesAutor(autor) }
            )
        }

        val possivelAutor = autorRepository.findByEmail(email)

        if(possivelAutor.isEmpty) return HttpResponse.notFound()

        val autor = possivelAutor.get()

        return HttpResponse.ok(DetalhesAutor(autor))
    }
}