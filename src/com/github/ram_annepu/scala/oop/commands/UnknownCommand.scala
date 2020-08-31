package com.github.ram_annepu.scala.oop.commands

import com.github.ram_annepu.scala.oop.filesystem.State

class UnknownCommand extends Command {

  override def apply(state:State):State =
    state.setMessage("Command not found")

}
