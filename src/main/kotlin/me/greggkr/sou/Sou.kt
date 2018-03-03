package me.greggkr.sou

import me.diax.comportment.jdacommand.CommandHandler
import me.greggkr.sou.listeners.CommandListener
import me.greggkr.sou.util.CommandReg
import me.greggkr.sou.util.Config
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game

private lateinit var jda: JDA
private val config = Config()

fun Array<String>.main() {
    val commandHandler = CommandHandler()
    commandHandler.registerCommands(CommandReg().getCommands())

    config.load()

    jda = JDABuilder(AccountType.BOT)
            .setToken(config.getProperty("token"))
            .setGame(Game.of(Game.GameType.WATCHING, "people play osu!"))
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .addEventListener(
                    CommandListener(commandHandler)
            )
            .buildBlocking()
}