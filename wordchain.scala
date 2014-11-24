// wordchain.scala
import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

val filename = "input.txt"
var wordlist = new ArrayBuffer[String]

for (line <- Source.fromFile(filename).getLines()) {
  if (line.length > 1) wordlist ++= line.trim.split(" ") 
}
println(wordlist)

#TODO: Generate node graph and then write to file.
