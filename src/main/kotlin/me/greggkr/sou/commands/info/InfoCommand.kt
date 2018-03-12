package me.greggkr.sou.commands.info

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.util.Data
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Message

@CommandDescription(name = "info", triggers = [
    "info", "i"
], description = "Displays information about the bot.")
class InfoCommand : Command {
    override fun execute(message: Message, a: String) {
        message.channel.sendMessage(EmbedBuilder()
                .setColor(Data.color)
                .setDescription("Made for the guild 2017.07.23.\n" +
                        "Made by ${message.jda.getUserById(184041169796333568).asMention}.")
                .setFooter("Made using Kotlin and JDA", null)
                .build()).queue()
    }
}