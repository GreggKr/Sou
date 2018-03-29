package me.greggkr.sou.util

import com.natpryce.konfig.PropertyGroup
import com.natpryce.konfig.getValue
import com.natpryce.konfig.stringType

class Config {
    object bot : PropertyGroup() {
        val discordToken by stringType
        val osuToken by stringType
        val tbaKey by stringType
    }
}