package com.github.ram_annepu.scala.oop.filesystem

import com.github.ram_annepu.scala.oop.files.Directory

class State (val root:Directory, val wd:  Directory, val output: String){ //holds state of the world

  def show: Unit = {
    println(output)
    print(State.SHELL_TOKEN)
  }
  def setMessage(message:String): State =
    State(root, wd, message)
}

object State{ //state factory object
  val SHELL_TOKEN = "$ "
  def apply(root: Directory, wd:Directory, output: String = ""): State =
    new State(root,wd,output)
}
