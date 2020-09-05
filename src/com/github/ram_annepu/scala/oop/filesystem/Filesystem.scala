package com.github.ram_annepu.scala.oop.filesystem

import java.util.Scanner

import com.github.ram_annepu.scala.oop.commands.Command
import com.github.ram_annepu.scala.oop.files.Directory

object Filesystem extends App { //App is a utility class which already has main method

  val root = Directory.ROOT
  var state = State(root,root)//whenever we make stateful changes we make use of vars
  //because we are changing a state and storing the intermittent state
  val scanner = new Scanner(System.in)

  while(true) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }


}
