package br.com.zupacademy.maxley.controller

import br.com.zupacademy.maxley.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import javax.transaction.Transactional

@Controller("/autores/{id}")
class DeletaAutorController(val autorRepository: AutorRepository) {

    @Delete
    @Transactional
    fun deleta(@PathVariable("id") id: Long): HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(id)
        if(possivelAutor.isEmpty) return HttpResponse.notFound()
        autorRepository.deleteById(id)

        return HttpResponse.noContent()
    }
}