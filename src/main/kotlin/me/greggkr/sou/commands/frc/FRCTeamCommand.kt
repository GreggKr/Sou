package me.greggkr.sou.commands.frc

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.Sou
import me.greggkr.sou.util.Data
import me.greggkr.sou.util.Emoji
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Message

@CommandDescription(name = "frcteam", triggers = [
    "frcteam", "team"
], description = "Gets information about a team.")
class FRCTeamCommand : Command {
    override fun execute(message: Message, a: String) {
        val channel = message.channel

        if (a.isEmpty()) {
            channel.sendMessage("${Emoji.X} **Correct usage: s!team <team number>")
            return
        }

        val team = Sou.tba.getTeam(Integer.parseInt(a))

        if (team == null) {
            channel.sendMessage("${Emoji.X} **Team not found.**").queue()
            return
        }

        if (team.name == null) {
            channel.sendMessage("${Emoji.X} **Team not found.**").queue()
            return
        }

        channel.sendMessage(EmbedBuilder()
                .setColor(Data.color)
                .addField("Name", team.nickname ?: team.name, true)
                .addField("Location", "${team.city}, ${team.providence}, ${team.country}", true)
                .addField("Rookie Year", "${team.firstYear}", true)
                .addField("Website", team.website ?: "None", true)
                .addField("2018 Championship", team.homeChampionships["2018"], true)
                .build())
                .queue()
    }
}