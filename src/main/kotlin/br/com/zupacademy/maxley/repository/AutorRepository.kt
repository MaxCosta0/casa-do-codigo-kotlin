package br.com.zupacademy.maxley.repository

import br.com.zupacademy.maxley.model.Autor
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository: JpaRepository<Autor, Long> {
    fun findByEmail(email: String): Optional<Autor>

    @Query("SELECT autor FROM Autor autor WHERE autor.email = :email")
    fun buscaPorEmail(email: String): Optional<Autor>
}