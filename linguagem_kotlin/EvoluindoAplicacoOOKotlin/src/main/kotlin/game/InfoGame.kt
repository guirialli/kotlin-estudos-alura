package org.example.game

import org.example.game.data.InfoApiShark

class InfoGame (val info: InfoApiShark) {
    override fun toString(): String {
        return info.toString();
    }
}