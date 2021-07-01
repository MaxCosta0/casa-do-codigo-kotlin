package br.com.zupacademy.maxley.controller

import br.com.zupacademy.maxley.controller.dto.DetalhesAutor
import br.com.zupacademy.maxley.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.transaction.Transactional

@Controller("/autores/{id}")
class AutalizaAutorController(val autorRepository: AutorRepository) {

    @Put
    @Transactional
    fun atualiza(
        @PathVariable("id") id: Long,
        descricao: String
    ): HttpResponse<DetalhesAutor> {
        val possivelAutor = autorRepository.findById(id)

        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        autor.descricao = descricao

        return HttpResponse.ok(DetalhesAutor(autor))
    }
}