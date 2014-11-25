// wordchain.scala
import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

val filename = "input.txt"
var wordlist = new ArrayBuffer[String]
var wordMap = scala.collection.mutable.Map[String, ArrayBuffer[String]]()

for (line <- Source.fromFile(filename).getLines()) {
  if (line.length > 1) wordlist ++= line.trim.split(" ") 
}
//println(wordlist)

// Generate node graph and then write to file.

for (word <- wordlist) {
  createNode(wordlist, word)
}


def createNode(wordlist : ArrayBuffer[String], word : String) {
  wordMap(word) = new ArrayBuffer[String]
  for (candidate <- wordlist) {
    if (neighbours(word, candidate)) {
      wordMap(word) += candidate
    }
  }
}

def neighbours( x : String, y : String) : Boolean = {
  if (x.length != y.length) {
    return false
  }
  else {
    var diff = 0
    for (i <- 0 to x.length - 1) {
      if (x(i) != y(i)) {
        diff += 1
      }
    }
    diff <= 1
  }
}

def findPath( startWord : String, endWord : String, maxDepth : Int) : (Boolean, Array[String]) = {
  if (maxDepth == 0) {
    return (false, Array())
  }
  if (wordMap(startWord) contains endWord) {
    return (true, Array(startWord, endWord))
  }
  else {
    for (word <- wordMap(startWord)) {
      val (res1, res2) = findPath(word, endWord, maxDepth - 1)
      if (res1 == true) {
        return (res1, startWord +: res2)
      }
    }
    return (false, Array())
  }
}

println(findPath("HATE", "LOVE", 5)._2.deep.mkString("\n"))
