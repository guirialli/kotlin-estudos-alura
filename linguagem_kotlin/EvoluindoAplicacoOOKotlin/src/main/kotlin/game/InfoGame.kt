package org.example.game

import game.data.PriceApiShark
import org.example.game.data.InfoApiShark

class InfoGame (val info: InfoApiShark, val cheapestPriceEver: PriceApiShark) {
    override fun toString(): String {
        return info.toString();
    }
}