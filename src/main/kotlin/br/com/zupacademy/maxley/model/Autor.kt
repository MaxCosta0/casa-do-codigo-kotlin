package br.com.zupacademy.maxley.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Autor(
    val nome: String,
    val email: String,
    var descricao: String,
    val cep: String,
    val endereco: Endereco
){
    @Id @GeneratedValue
    var id: Long? = null

    val criadoEm: LocalDateTime = LocalDateTime.now()

    override fun toString(): String {
        return "Autor(nome='$nome', email='$email', descricao='$descricao', cep='$cep', endereco=$endereco, id=$id, criadoEm=$criadoEm)"
    }


}
