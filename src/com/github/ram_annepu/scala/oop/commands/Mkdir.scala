package com.github.ram_annepu.scala.oop.commands

import com.github.ram_annepu.scala.oop.files.{DirEntry, Directory}
import com.github.ram_annepu.scala.oop.filesystem.State

class Mkdir(name:String) extends Command {
  override def apply(state:State):State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage("Entry "+name+" already exists")
    }
    else if (name.contains(Directory.SEPARATOR)){
      state.setMessage(name+" must not contain separators")
    }
    else if (checkIllegal(name)){
      state.setMessage(name +" is illegal entry")
    }
    else {
      println("inside mkdir")
      doMkdir(state, name)
    }
  }

  def checkIllegal(str: String):Boolean={
    str.contains(".")
  }

  def doMkdir(state:State, name:String):State ={
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry):Directory = {
      if(path.isEmpty) {
        println("inside update structure")
        currentDirectory.addEntry(newEntry)

      }
      else {
        println("inside update structure")
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }

    }

    val  wd = state.wd
    //steps
    //1. get hold of all the directories in full path
    val allDirsInPath = wd.getAllFoldersInPath

    //2. create new directory entry in working directory
    val newDir = Directory.empty(wd.path,name)

    //3. update the whole directory structure from the root with new directory
    //(as the directory structure is immutable, we need to recreate entire structure on a change)
    val newRoot = updateStructure(state.root, allDirsInPath,newDir)

    //4. find new working directory instance in the new directory structure
    val newWd = wd.findDescendant(allDirsInPath)

    State(newRoot,newWd)

  }

}
