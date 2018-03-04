package me.greggkr.sou.util

enum class Emoji(private val unicode: String) {
    X("\u274C");

    override fun toString(): String {
        return unicode
    }
}