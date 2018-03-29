package me.greggkr.sou.commands.osu

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.Sou
import me.greggkr.sou.osu.Mode
import me.greggkr.sou.osu.wrappers.User
import me.greggkr.sou.util.Data
import me.greggkr.sou.util.Emoji
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Message
import java.text.DecimalFormat

@CommandDescription(name = "user", triggers = [
    "user", "u"
], description = "Gets information about an osu! user.")
class UserCommand : Command {
    private val decFormat = DecimalFormat("######.###")
    override fun execute(message: Message, a: String) {
        if (a.isEmpty()) {
            message.channel.sendMessage("${Emoji.X} **Correct usage: s!user <name>.**").queue()
            return
        }

        val avatar = Data.getProfilePicture(a)

        if (avatar == null) {
            message.channel.sendMessage("user doesnt exist or doesnt have profile pic").queue()
            return
        }

        val user: User? = Sou.osu.getUser(Mode.STANDARD, a)

        if (user == null) {
            message.channel.sendMessage("${Emoji.X} **User not found.**").queue()
            return
        }

        message.channel.sendMessage(EmbedBuilder()
                .setColor(Data.color)
                .setThumbnail(avatar)
                .addField("Info", "**Name**: ${user.name}\n" +
                        "**PP**: ${decFormat.format(user.pp)}\n" +
                        "**Rank**: ${user.rank}\n" +
                        "**Level**: ${decFormat.format(user.level)}\n" +
                        "**Rank in ${user.country}**: ${user.countryRank}\n" +
                        "**Acc**: ${decFormat.format(user.accuracy)}%\n", true)
                .build()).queue()
    }
}