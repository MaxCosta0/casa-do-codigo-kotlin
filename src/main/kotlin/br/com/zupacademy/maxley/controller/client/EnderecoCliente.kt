package br.com.zupacademy.maxley.controller.client

import br.com.zupacademy.maxley.controller.dto.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client(value = "http://localhost:8081/enderecos/cep")
interface EnderecoCliente {

    @Get("/{cep}")
    fun endereco(cep: String): HttpResponse<EnderecoResponse>

}
