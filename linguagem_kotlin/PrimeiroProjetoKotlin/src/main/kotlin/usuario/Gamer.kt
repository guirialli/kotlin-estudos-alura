package org.example.usuario

import java.util.UUID

class Gamer(
    var nome: String,
    var email: String,
    var dataNascimento: String? = null,
    var usuario: String? = null,
    private var idIterno: String? = UUID.randomUUID().toString()
) {

    constructor(nome: String, email: String, dataNascimento: String?, usuario: String?) :
            this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
    }

    init {
        if(nome.isBlank())
            throw IllegalArgumentException("Nome não pode ser nulo, ou, em branco!")
        this.email = validarEmail()
    }

    fun getIdInterno(): String? {
        return this.idIterno
    }

    private fun validarEmail(): String{
        val regexEmail =  Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$" )
        if(regexEmail.matches(email))
            return email
        else
            throw  IllegalArgumentException("Email invalido!")
    }

    override fun toString(): String {
        return "Gamer(nome = '${nome}', email='${email}', dataNascimento=${dataNascimento}, ususario='${usuario}'idInterno='${idIterno}'"
    }

}