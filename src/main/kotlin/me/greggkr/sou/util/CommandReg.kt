package me.greggkr.sou.util

import me.diax.comportment.jdacommand.Command
import me.greggkr.sou.commands.frc.FRCRobotCommand
import me.greggkr.sou.commands.frc.FRCRobotsCommand
import me.greggkr.sou.commands.frc.FRCTeamCommand
import me.greggkr.sou.commands.frc.TBAStatusCommand
import me.greggkr.sou.commands.info.InfoCommand
import me.greggkr.sou.commands.info.StatsCommand
import me.greggkr.sou.commands.osu.ProfilePictureCommand
import me.greggkr.sou.commands.osu.UserCommand
import me.greggkr.sou.commands.test.ImgOverlayCommand

class CommandReg {
    private val commands = HashSet<Command>()

    init {
        register(
                InfoCommand(),
                StatsCommand(),
                UserCommand(),
                ProfilePictureCommand(),
                FRCTeamCommand(),
                ImgOverlayCommand(),
                TBAStatusCommand(),
                FRCRobotCommand(),
                FRCRobotsCommand()
        )
    }

    private fun register(vararg command: Command) {
        commands.addAll(command)
    }

    fun getCommands(): Set<Command> = commands
}