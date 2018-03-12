package me.greggkr.sou.util

import java.awt.Graphics2D
import java.awt.image.BufferedImage

class FileUtil {
    companion object {
        fun resizeImage(image: BufferedImage, width: Int, height: Int): BufferedImage {
            val scaledImage = BufferedImage(width, height, image.type)

            val graphics: Graphics2D = scaledImage.createGraphics()
            graphics.drawImage(image, 0, 0, width, height, null)
            graphics.dispose()

            return scaledImage
        }
    }
}