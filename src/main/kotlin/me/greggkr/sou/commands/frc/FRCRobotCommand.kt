package me.greggkr.sou.commands.frc

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.Sou
import me.greggkr.sou.util.Data
import me.greggkr.sou.util.Emoji
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Message

@CommandDescription(name = "frcrobot", triggers = [
    "frcbot", "robot", "bot"
], description = "Gets information about a team's robot for a particular year.")
class FRCRobotCommand : Command {
    override fun execute(message: Message, a: String) {
        val channel = message.channel

        if (a.isEmpty()) {
            channel.sendMessage("**${Emoji.X} Correct usage: s!robot <team number> <year>**").queue()
            return
        }

        val args = a.split(Regex("\\s+"))

        if (args.size < 2) {
            channel.sendMessage("**${Emoji.X} Correct usage: s!robot <team number> <year>").queue()
            return
        }

        val team: Int
        val year: Int

        try {
            team = args[0].toInt()
            year = args[1].toInt()
        } catch (e: NumberFormatException) {
            channel.sendMessage("**${Emoji.X} Correct usage: s!robot <team number> <year>**").queue()
            return
        }

        val robot = Sou.tba.getRobot(team, year)

        if (robot == null) {
            channel.sendMessage("${Emoji.X} **Team or robot not found.**").queue()
            return
        }

        channel.sendMessage(EmbedBuilder()
                .setColor(Data.color)
                .addField("Name", robot.name, true)
                .build())
                .queue()
    }
}