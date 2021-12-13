package aoc

class Day01 : Day(1) {

    /*
    counts the total number of times a measurement increased from the previous one
     */
    override fun part1(input: List<String>): Int {
        var last = Integer.MAX_VALUE
        return input.map(String::toInt).count { val p = it > last; last = it; p }
    }

    /*
    counts the total number of times a 3-wide sum increased from the previous sum
     */
    override fun part2(input: List<String>): Int =
        input.map(String::toInt).run{
            var last = Integer.MAX_VALUE
            (0..input.size-3).count { val s = this[it]+this[it+1]+this[it+2]; val p = s > last; last = s; p }
        }

    override fun check(input:List<String>): Boolean {
        // test if implementation meets criteria from the description
        val testInput = readInput("Day01_test")
        var success = (part1(testInput) == 7)
        success = success and (part2(testInput) == 5)
        return success
    }

}
