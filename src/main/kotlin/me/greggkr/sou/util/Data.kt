package me.greggkr.sou.util

import java.awt.Color
import java.util.*

class Data {
    companion object {
        private val owners: List<Long> = Arrays.asList(
                184041169796333568L
        )
        val color = Color(200, 66, 244)

        fun isOwner(id: Long): Boolean {
            return owners.contains(id)
        }

        fun isOwner(id: String): Boolean {
            return owners.contains(id.toLong())
        }
    }
}