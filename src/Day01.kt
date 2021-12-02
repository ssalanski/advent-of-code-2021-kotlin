fun main() {
    /*
    counts the total number of times a measurement increased from the previous one
     */
    fun part1(input: List<String>): Int {
        var last = Integer.MAX_VALUE
        return input.map(String::toInt).count { val p = it > last; last = it; p }
    }

    /*
    counts the total number of times a 3-wide sum increased from the previous sum
     */
    fun part2(input: List<String>): Int {
        var last = Integer.MAX_VALUE
        var result = 0
        for (index in 0..input.size-3) {
            val sum = input[index].toInt() + input[index+1].toInt() + input[index+2].toInt()
            if (sum > last) {
                result++
            }
            last = sum
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println("part 1: ${part1(input)}")
    println("part 2: ${part2(input)}")
}
