package me.greggkr.sou

import com.natpryce.konfig.ConfigurationProperties
import me.diax.comportment.jdacommand.CommandHandler
import me.greggkr.sou.frc.TBA
import me.greggkr.sou.listeners.CommandListener
import me.greggkr.sou.osu.Osu
import me.greggkr.sou.util.CommandReg
import me.greggkr.sou.util.Config
import me.greggkr.sou.util.Data
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game
import java.io.File

class Sou {
    companion object {
        var startTime: Long = Long.MIN_VALUE
        lateinit var jda: JDA
        lateinit var osu: Osu
        lateinit var tba: TBA
        lateinit var config: ConfigurationProperties

        fun getUptime(): String {
            val uptime = System.currentTimeMillis() - startTime
            val s = uptime / 1000
            val m = if (s >= 60) s / 60 else 0
            val h = if (m >= 60) m / 60 else 0
            val d = if (h >= 60) h / 60 else 0

            return "${d}d:${h}h:${m}m:${s}s"
        }
    }

    fun start() {
        startTime = System.currentTimeMillis()
        config = ConfigurationProperties.fromFile(File("config.properties"))
        osu = Osu(config[Config.bot.osuToken])
        tba = TBA(config[Config.bot.tbaKey])

        val commandHandler = CommandHandler()
        commandHandler.registerCommands(CommandReg().getCommands())

        // clears cache when exits
        Runtime.getRuntime().addShutdownHook(Thread { Data.clearCache() })

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