package me.greggkr.sou.commands.frc

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.Sou
import me.greggkr.sou.util.Data
import me.greggkr.sou.util.Emoji
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Message

@CommandDescription(name = "frcrobots", triggers = [
    "frcrobots", "robots", "bots"
], description = "Gets information about a team's robot for all years.")
class FRCRobotsCommand : Command {
    override fun execute(message: Message, a: String) {
        val channel = message.channel

        if (a.isEmpty()) {
            channel.sendMessage("**${Emoji.X} Correct usage: s!robots <team number>**").queue()
            return
        }

        val team: Int

        try {
            team = a.toInt()
        } catch (e: NumberFormatException) {
            channel.sendMessage("${Emoji.X} **Correct Usage: s!robots <team number>**").queue()
            return
        }

        val robots = Sou.tba.getRobots(team)

        if (robots == null) {
            channel.sendMessage("${Emoji.X} **Robots not found.**").queue()
            return
        }

        val embed = EmbedBuilder()
                .setColor(Data.color)

        for (r in robots) {
            embed.addField("${r.year}", r.name, true)
        }

        channel.sendMessage(embed
                .build())
                .queue()
    }
}