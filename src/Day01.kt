fun main() {
    fun part1(input: List<String>): Int {
        var last = Integer.MAX_VALUE
        var result = 0
        for (measurement in input) {
            if (measurement.toInt() > last) {
                result++
            }
            last = measurement.toInt()
        }
        return result
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println("part 1: ${part1(input)}")
    println("part 2: ${part2(input)}")
}
