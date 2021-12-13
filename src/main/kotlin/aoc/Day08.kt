package aoc

import kotlin.math.absoluteValue

class Day08 : Day(8) {

  private fun parseLine(line: String): Pair<List<Set<Char>>, List<Set<Char>>> {
    return line.split('|').map { it.split(" ").map(String::toSet) }.run { first() to last() }
  }

  override fun part1(input: List<String>): Int {
    return input.sumOf{ line ->
      val (observed, asdf) = parseLine(line)
      countUnique(asdf)
    }
  }

  private fun countUnique(asdf: List<Set<Char>>) : Int {
    return asdf.count { (it.size == 2) or (it.size == 3) or (it.size == 4) or (it.size == 7) }
  }

  override fun part2(input: List<String>): Int {
    return 0
  }

  override fun check1(input: List<String>): Boolean {
    return (part1(input).also { println(it) } == 26)
  }

  override fun check2(input: List<String>): Boolean {
    return (part2(input) == 88888888)
  }
}
