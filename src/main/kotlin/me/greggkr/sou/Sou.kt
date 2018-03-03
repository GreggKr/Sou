package me.greggkr.sou

import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game
import util.Config

private lateinit var jda: JDA
private val config = Config()

fun Array<String>.main() {
    config.load()

    jda = JDABuilder(AccountType.BOT)
            .setToken(config.getProperty("token"))
            .setGame(Game.of(Game.GameType.WATCHING, "people play osu!"))
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .buildBlocking()
}