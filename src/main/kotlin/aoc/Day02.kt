package aoc

class Day02 : Day(2) {
    override fun part1(input: List<String>): Int {
        var horizontalPos = 0
        var depth = 0
        input.forEach { line ->
            val (direction,distance) = line.split(" ")
            if (direction == "up") {
                depth -= distance.toInt()
            }
            else if (direction == "down") {
                depth += distance.toInt()
            }
            else if (direction == "forward") {
                horizontalPos += distance.toInt()
            }
        }
        return depth * horizontalPos
    }

    override fun part2(input: List<String>): Int {
        var horizontalPos = 0
        var depth = 0
        var aim = 0
        input.forEach { line ->
            val (direction,amount) = line.split(" ")
            if (direction == "up") {
                aim -= amount.toInt()
            }
            else if (direction == "down") {
                aim += amount.toInt()
            }
            else if (direction == "forward") {
                horizontalPos += amount.toInt()
                depth += aim * amount.toInt()
            }
        }
        return depth * horizontalPos
    }

    override fun check1(input: List<String>): Boolean {
        return (part1(input) == 150)
    }
    override fun check2(input: List<String>): Boolean {
        return (part2(input) == 900)
    }

}
