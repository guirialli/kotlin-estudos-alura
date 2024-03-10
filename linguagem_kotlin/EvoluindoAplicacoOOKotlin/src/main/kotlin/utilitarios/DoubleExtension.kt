@file:Suppress("DEPRECATION")

package utilitarios

import java.text.NumberFormat
import java.util.*

fun Double.toBRL(): String? {
    val ptBr = Locale("pt", "BR")
    val formatador = NumberFormat.getCurrencyInstance(ptBr)
    return formatador.format(this)
}