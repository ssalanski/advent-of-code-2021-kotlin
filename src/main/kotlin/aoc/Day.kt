package aoc

abstract class Day(dayNumber: Int) {

    abstract fun part1(input: List<String>): Any

    abstract fun part2(input: List<String>): Any

    abstract fun check1(input: List<String>): Boolean
    abstract fun check2(input: List<String>): Boolean
}
