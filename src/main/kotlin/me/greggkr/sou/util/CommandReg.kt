package me.greggkr.sou.util

import me.diax.comportment.jdacommand.Command
import me.greggkr.sou.commands.InfoCommand
import me.greggkr.sou.commands.osu.UserCommand

class CommandReg {
    private val commands = HashSet<Command>()

    init {
        register(
                InfoCommand(),
                UserCommand()
        )
    }

    private fun register(vararg command: Command) {
        commands.addAll(command)
    }

    fun getCommands(): Set<Command> = commands
}