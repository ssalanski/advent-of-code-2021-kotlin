fun main() {
    fun part1(input: List<String>): Int {
        var differentials : ArrayList<Int> = ArrayList(input[0].length)
        input.forEach { line ->
            line.forEachIndexed { i, v -> 
                differentials[i] += 
                    if (v == '1')
                        1
                    else
                        -1
            }
        }
        var gamma = 0
        var epsilon = 0
        differentials.reversed().forEachIndexed { i, d ->
            if( d > 0)
                gamma = gamma or 1 shl i
            else
                epsilon = epsilon or 1 shl i
        }
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    //check(part2(testInput) == 333)

    val input = readInput("Day03")
    println("part 1: ${part1(input)}")
    println("part 2: ${part2(input)}")
}
