package aoc

class Day03 : Day(3) {
    override fun part1(input: List<String>): Int {
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

    override fun part2(input: List<String>): Int {
        return 0
    }

    override fun check(input: List<String>): Boolean {
        // test if implementation meets criteria from the description
        val testInput = readInput("Day03_test")
        var success = (part1(testInput) == 198)
        success = success and (part2(testInput) == 333)
        return success
    }
}
