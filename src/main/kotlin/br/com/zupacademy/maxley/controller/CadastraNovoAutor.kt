package br.com.zupacademy.maxley.controller

import br.com.zupacademy.maxley.controller.client.EnderecoCliente
import br.com.zupacademy.maxley.controller.dto.EnderecoResponse
import br.com.zupacademy.maxley.controller.dto.NovoAutorRequest
import br.com.zupacademy.maxley.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraNovoAutor(
    val autorRepository: AutorRepository,
    val enderecoCliente: EnderecoCliente
) {
    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {

        val enderecoResponse: HttpResponse<EnderecoResponse> = enderecoCliente.endereco(request.cep)

        if(enderecoResponse.body() == null){
            return HttpResponse.badRequest()
        }

        val autor = request.toAutor(enderecoResponse.body())
        autorRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        println(enderecoResponse.body())
        return HttpResponse.created(uri)


//        enderecoResponse.body()?.let {
//            val autor = request.toAutor(it)
//            autorRepository.save(autor)
//            val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
//            println(enderecoResponse.body())
//            return HttpResponse.created(uri)
//        }

//        return HttpResponse.badRequest()
    }
}