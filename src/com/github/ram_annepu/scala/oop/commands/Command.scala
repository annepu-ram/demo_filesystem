package com.github.ram_annepu.scala.oop.commands

import com.github.ram_annepu.scala.oop.filesystem.State

trait Command {
  //commands can transform the state of the world from one state to another
  def apply(state: State): State
}

object Command {
  val MKDIR = "mkdir" //use constants instead of literals
  val LS = "ls"

  def emptyCommand: Command = new Command {
    override def apply(state: State): State = state
  }

  def incompleteCommand(name:String):Command = new Command{
    override def apply(state: State): State = state.setMessage(name + ": incomplete command")
  }

  def from(input:String): Command= {
    val tokens:Array[String] = input.split(" ")

    if(input.isEmpty || tokens.isEmpty) emptyCommand  //token always has empty string so use input

    else if(MKDIR.equals(tokens(0))) {
      if(tokens.length<2) incompleteCommand(MKDIR)
      else new Mkdir(tokens(1))
    }
    else if(LS.equals(tokens(0))){
       new Ls
    }
    else new UnknownCommand
  }
}