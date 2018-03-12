package me.greggkr.sou.commands.info

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.Sou
import me.greggkr.sou.util.Data
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Message
import oshi.SystemInfo
import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean

@CommandDescription(name = "stats", triggers = [
    "stats"
], description = "Displays statistics about the bot.")
class StatsCommand : Command {
    override fun execute(message: Message, args: String) {
        val info = SystemInfo()
        val os = info.operatingSystem
        val sunOSBean = ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean

        message.channel.sendMessage(EmbedBuilder()
                .setColor(Data.color)
                .setAuthor("Sou | Random fun things for 2.0.2", null, message.jda.selfUser.effectiveAvatarUrl)
                .addField("Bot", "Uptime: ${Sou.getUptime()}", true)
                .addField("System", "**OS**\n" +
                        "Name: ${os.family}\n" +
                        "Version: ${os.version.version}\n" +
                        "Arch: ${sunOSBean.arch}", true)
                .build())
                .queue()
    }
}