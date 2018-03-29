package me.greggkr.sou.commands.test

import me.diax.comportment.jdacommand.Command
import me.diax.comportment.jdacommand.CommandDescription
import me.greggkr.sou.util.ImageUtil
import net.dv8tion.jda.core.entities.Message
import java.io.File
import javax.imageio.ImageIO

@CommandDescription(name = "imgoverlay", triggers = [
    "imgoverlay"
], description = "test command for testing overlay")
class ImgOverlayCommand : Command {
    override fun execute(message: Message, a: String) {
        val back = ImageIO.read(File("img/test/back.png"))
        val top = ImageIO.read(File("img/test/top.png"))

        val image = ImageUtil.overlay(back, top, 50, 50)

        val time = System.currentTimeMillis()

        val file = File("img/edited/overlay-$time.png")

        ImageIO.write(image, "PNG", file)

        message.channel.sendFile(file).queue()
    }
}