fun main() {
    fun part1(input: List<String>): Int {
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

    fun part2(input: List<String>): Int {
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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println("part 1: ${part1(input)}")
    println("part 2: ${part2(input)}")
}
