package me.greggkr.sou

import com.natpryce.konfig.ConfigurationProperties
import me.diax.comportment.jdacommand.CommandHandler
import me.greggkr.sou.listeners.CommandListener
import me.greggkr.sou.osu.Osu
import me.greggkr.sou.util.CommandReg
import me.greggkr.sou.util.Config
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game
import java.io.File

class Sou {
    companion object {
        lateinit var jda: JDA
        lateinit var osu: Osu
        lateinit var config: ConfigurationProperties
    }

    fun start() {
        config = ConfigurationProperties.fromFile(File("config.properties"))
        osu = Osu(config[Config.bot.osuToken])

        val commandHandler = CommandHandler()
        commandHandler.registerCommands(CommandReg().getCommands())


        jda = JDABuilder(AccountType.BOT)
                .setToken(config[Config.bot.discordToken])
                .setGame(Game.of(Game.GameType.WATCHING, "people play osu!"))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .addEventListener(CommandListener(commandHandler))
                .buildBlocking()
    }
}

fun main(args: Array<String>) {
    Sou().start()
}