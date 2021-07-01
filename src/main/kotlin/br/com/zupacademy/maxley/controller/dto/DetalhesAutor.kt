package br.com.zupacademy.maxley.controller.dto

import br.com.zupacademy.maxley.model.Autor
import io.micronaut.core.annotation.Introspected

class DetalhesAutor(autor: Autor){
    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}
