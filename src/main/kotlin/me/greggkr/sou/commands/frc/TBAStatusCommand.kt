package me.greggkr.sou.commands.frc

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.Sou
import me.greggkr.sou.util.Data
import me.greggkr.sou.util.Emoji
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Message
import java.util.*

@CommandDescription(name = "tbastatus", triggers = [
    "tbastatus", "tba"
], description = "Gets TBA API status information")
class TBAStatusCommand : Command {
    override fun execute(message: Message, a: String) {
        val channel = message.channel
        val status = Sou.tba.getTBAStatus()

        if (status == null) {
            channel.sendMessage("${Emoji.X} **TBA Status returned nothing.**").queue()
            return
        }

        channel.sendMessage(EmbedBuilder()
                .setColor(Data.color)
                .addField("Current Season", "${status.currentSeason}", true)
                .addField("Datafeed Status", if (status.datafeedDown) "Down" else "Up", true)
                .addField("Down Events", if (status.downEvents.isEmpty()) "None" else Arrays.toString(status.downEvents), true)
                .build())
                .queue()
    }
}