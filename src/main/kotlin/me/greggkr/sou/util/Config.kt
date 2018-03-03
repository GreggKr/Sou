package me.greggkr.sou.util

import java.io.FileInputStream
import java.util.*

class Config {
    val props = Properties()

    fun load() {
        val fis = FileInputStream("config.properties")
        props.load(fis)
        fis.close()
    }

    fun getProperty(key: String): String {
        return props.getProperty(key)
    }
}