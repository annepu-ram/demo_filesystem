package com.github.ram_annepu.scala.oop.commands

import com.github.ram_annepu.scala.oop.filesystem.State

trait Command {
  //commands can transform the state of the world from one state to another
  def apply(state: State): State
}
object Command {
  def from(input:String): Command=
    new UnknownCommand
}