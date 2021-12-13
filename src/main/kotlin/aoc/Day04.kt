package aoc

import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

class Day04 : Day(4) {

  private fun parseLines(
    input: List<String>,
    orthogonalOnly: Boolean
  ): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
    val pattern = Regex("^([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)")
    val lines = input.mapNotNull {
      pattern.matchEntire(it)?.let { match ->
        Pair(
          Pair(match.groupValues[1].toInt(), match.groupValues[2].toInt()),
          Pair(match.groupValues[3].toInt(), match.groupValues[4].toInt())
        )
      }
    }
      .filter { line ->
        if (orthogonalOnly) {
          val (p, q) = line
          (p.first == q.first) or (p.second == q.second)
        } else {
          true
        }
      }
    return lines
  }

  private fun fillGrid(lines: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>): Array<Array<Int>> {
    val gridsize = lines.flatMap { line ->
      listOf(line.first.first, line.first.second, line.second.first, line.second.second)
    }.maxOrNull()!! + 1
    val grid = Array(gridsize) { Array(gridsize) { 0 } }
    lines.forEach { line ->
      val dx = line.second.first - line.first.first
      val dy = line.second.second - line.first.second
//      println("${line} => ${dx},${dy}")
//      if (dx == 0) {
//        val range =
//          if (dy < 0)
//            line.second.second..line.first.second
//          else
//            line.first.second..line.second.second
//        for (y in range) {
//          println("filling ${line.first.first},${y}")
//          grid[line.first.first][y]++
//        }
//      } else if (dy == 0) {
//        val range =
//          if (dx < 0)
//            line.second.first..line.first.first
//          else
//            line.first.first..line.second.first
//        for (x in range) {
//          println("filling ${x},${line.first.first}")
//          grid[x][line.first.second]++
//        }
//      } else {
        val xstep = dx.sign
        val ystep = dy.sign
        val steps = max(dx.absoluteValue,dy.absoluteValue)
        var x = line.first.first
        var y = line.first.second
        for (step in 0..steps) {
//          println("filling ${x},${y}")
          grid[y][x]++
          x += xstep
          y += ystep
        }
//      }
    }
    return grid
  }

  private fun countMax(grid: Array<Array<Int>>): Int {
    var max = 0
    var count = 0
    grid.flatMap { row -> row.asIterable() }.forEach {
      if (it == max) {
        count++
      } else if (it > max) {
        max = it
        count = 1
      }
    }
    return count
  }

  private fun countAtLeastTwo(grid: Array<Array<Int>>): Int {
    return grid.flatMap { row -> row.asIterable() }.count { it >= 2 }
  }

  override fun part1(input: List<String>): Int {
    val lines = parseLines(input, true)
//    lines.forEach { println(it) }
    val grid = fillGrid(lines)
//    grid.forEach { it.forEach { row -> print(row.toString()) }; println() }
    val result = countAtLeastTwo(grid)
    return result
  }

  override fun part2(input: List<String>): Int {
    val lines = parseLines(input, false)
//    lines.forEach { println(it) }
    val grid = fillGrid(lines)
//    grid.forEach { it.forEach { row -> print(row.toString()) }; println() }
    val result = countAtLeastTwo(grid)
//    println(result)
    return result
  }

  override fun check1(input: List<String>): Boolean {
    return (part1(input) == 5)
  }

  override fun check2(input: List<String>): Boolean {
    return (part2(input) == 12)
  }
}
