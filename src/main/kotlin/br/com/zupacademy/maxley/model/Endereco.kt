package br.com.zupacademy.maxley.model

import br.com.zupacademy.maxley.controller.dto.EnderecoResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco(
    enderecoResponse: EnderecoResponse,
    val numero: String
){
    val estado: String = enderecoResponse.estado
    val cidade: String = enderecoResponse.cidade
    val rua: String = enderecoResponse.rua
}
