package org.example.utilitarios

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.tranformarEmIdade(): Int{
    val formater = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var idade = 0
    val dataNascimento = LocalDate.parse(this, formater)
    val hoje = LocalDate.now()
    idade = Period.between(dataNascimento, hoje).years

    return idade
}