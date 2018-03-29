package me.greggkr.sou.util

import java.awt.Graphics2D
import java.awt.image.BufferedImage

class ImageUtil {
    companion object {
        fun resizeImage(image: BufferedImage, width: Int, height: Int): BufferedImage {
            val scaledImage = BufferedImage(width, height, image.type)

            val graphics: Graphics2D = scaledImage.createGraphics()
            graphics.drawImage(image, 0, 0, width, height, null)
            graphics.dispose()

            return scaledImage
        }

        /**
         * @param back Background image
         * @param top Image to overlay
         * @param startX Top left X of top image
         * @param startY Top left Y of top image
         *
         * @return new image
         */
        fun overlay(back: BufferedImage, top: BufferedImage, startX: Int, startY: Int): BufferedImage {
            val width = Math.max(back.width, top.width)
            val height = Math.max(back.height, top.height)

            val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

            val graphics = image.createGraphics()

            graphics.drawImage(back, 0, 0, null)
            graphics.drawImage(top, startX, startY, null)

            return image
        }
    }
}