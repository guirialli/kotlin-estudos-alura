package org.example.game

import com.google.gson.annotations.SerializedName

class Game(
    @SerializedName("title") val titulo: String,
    @SerializedName("thumb") val capa: String,
    var descricao: String? = null
) {

    override fun toString(): String {
        return "Game: \nTitulo $titulo \nCapa: $capa  \nDescricao: $descricao"
    }
}