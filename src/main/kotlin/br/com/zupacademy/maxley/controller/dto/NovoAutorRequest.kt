package br.com.zupacademy.maxley.controller.dto

import br.com.zupacademy.maxley.config.annotation.CepValido
import br.com.zupacademy.maxley.model.Autor
import br.com.zupacademy.maxley.model.Endereco
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:Email @field:NotBlank val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank @field:CepValido val cep: String,
    @field:NotBlank val numero: String
) {
    fun toAutor(enderecoResponse: EnderecoResponse): Autor {
        val enderecoCompleto = Endereco(enderecoResponse, numero)

        return Autor(
            nome = this.nome,
            email = this.email,
            descricao = this.descricao,
            cep = this.cep,
            endereco = enderecoCompleto
        )
    }
}
