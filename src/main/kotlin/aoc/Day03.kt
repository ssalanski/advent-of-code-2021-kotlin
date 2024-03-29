package aoc

class Day03 : Day(3) {
    override fun part1(input: List<String>): Int {
        val differentials = IntArray(input[0].length) { 0 }
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
                gamma = gamma or (1 shl i)
            else
                epsilon = epsilon or (1 shl i)
        }
        println("${gamma}*${epsilon} = ${gamma*epsilon}")
        return gamma * epsilon
    }

    override fun part2(input: List<String>): Int {
        return 0
    }

    override fun check1(input: List<String>): Boolean {
        return (part1(input) == 198)
    }
    override fun check2(input: List<String>): Boolean {
        return (part2(input) == 230)
    }
}
