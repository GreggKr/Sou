package me.greggkr.sou.commands.osu

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.Sou
import me.greggkr.sou.util.Emoji
import me.greggkr.sou.util.osu.Mode
import me.greggkr.sou.util.osu.wrappers.User
import net.dv8tion.jda.core.entities.Message

@CommandDescription(name = "user", triggers = [
    "user", "u"
], description = "Gets information about an osu! user.")
class UserCommand : Command {
    override fun execute(message: Message, a: String) {
        if (a.isEmpty()) {
            message.channel.sendMessage("${Emoji.X} **Correct usage: user <name>.**").queue()
            return
        }

        val name = a.split(Regex("\\s+"))[0]

        val user: User? = Sou.osu.getUser(Mode.STANDARD, name)

        if (user == null) {
            message.channel.sendMessage("${Emoji.X} **User not found.**").queue()
            return
        }

        message.channel.sendMessage("rank: ${user.rank}").queue()
    }
}