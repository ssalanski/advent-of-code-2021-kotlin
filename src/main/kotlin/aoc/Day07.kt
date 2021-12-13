package aoc

import kotlin.math.absoluteValue

class Day07 : Day(7) {

  private fun parseAndSortInput(input: List<String>): List<Int> {
    return input.flatMap { it.split(",") }.map { it.toInt() }.sorted()
  }

  private fun calculateCost(positions: List<Int>, alignment: Int) : Int {
    return positions.map{ it -> (it-alignment).absoluteValue }.sum()
  }

  private fun optimizeAlignment(positions: List<Int>): Int {
    var bestCost = Int.MAX_VALUE
    for(x in positions.first()..positions.last()) {
      val cost = calculateCost(positions,x)
      if(cost<bestCost){
        bestCost = cost
      }
    }
    println("best cost is ${bestCost}")
    return bestCost
  }

  override fun part1(input: List<String>): Int {
    val sortedPositions = parseAndSortInput(input)
    return optimizeAlignment(sortedPositions)
  }

  override fun part2(input: List<String>): Int {
    val sortedPositions = parseAndSortInput(input)
    return optimizeAlignment(sortedPositions)
  }

  override fun check1(input: List<String>): Boolean {
    return (part1(input) == 0)
  }

  override fun check2(input: List<String>): Boolean {
    return (part2(input) == 1)
  }
}
