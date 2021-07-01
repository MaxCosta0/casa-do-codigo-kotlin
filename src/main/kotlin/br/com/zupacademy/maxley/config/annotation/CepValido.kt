package br.com.zupacademy.maxley.config.annotation

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint

import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Constraint(validatedBy = [CepValidator::class])
@Retention(RUNTIME)
annotation class CepValido(val message: String = "o CEP precisa estar em um formato valido")

@Singleton
class CepValidator : ConstraintValidator<CepValido, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<CepValido>,
        context: ConstraintValidatorContext
    ): Boolean {

        if(value == null) return true

        //Formato esperado para o cep: 00000-000
       return value.matches("[0-9]{5}[-][0-9]{3}".toRegex())
    }

}
