package me.greggkr.sou.commands.osu

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.Sou
import me.greggkr.sou.osu.Mode
import me.greggkr.sou.util.Data
import me.greggkr.sou.util.Emoji
import net.dv8tion.jda.core.entities.Message

@CommandDescription(name = "profilepicture", triggers = [
    "profilepicture", "pfp"
], description = "Gets the profile picture of an osu! user.")
class ProfilePictureCommand : Command {
    override fun execute(message: Message, a: String) {
        val channel = message.channel

        if (a.isEmpty()) {
            channel.sendMessage("**Correct usage: s!pfp <name>**").queue()
            return
        }

        val user = Sou.osu.getUser(Mode.STANDARD, a) // mode doesn't matter here, just used for ID

        if (user == null) {
            channel.sendMessage("${Emoji.X} **User not found.**").queue()
            return
        }

        val avatar = Data.getCachedProfilePicture(user.id, a)
        if (avatar == null) {
            channel.sendMessage("${Emoji.X} **User does not have an avatar.**")
            return
        }

        channel.sendFile(avatar).queue()
    }
}