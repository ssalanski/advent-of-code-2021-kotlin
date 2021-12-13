package aoc

import org.reflections.Reflections
import kotlin.math.max
import kotlin.time.ExperimentalTime
import kotlin.time.TimedValue
import kotlin.time.measureTimedValue

@ExperimentalTime
object Runner {

    private val reflections = Reflections("aoc")

    @JvmStatic
    fun main(args: Array<String>) {
        var day = 0 // 0 indicates that all days should be run
        var testOnly = false
        if (args.isNotEmpty()) {
            if(args.size == 1) {
                if(args[0] == "test") {
                    testOnly = true
                }
                else {
                    day = try {
                        args[0].toInt()
                    } catch (e: NumberFormatException) {
                        printError("argument must be an integer day number, or 'test'")
                        return
                    }
                }
            }
            else {
                day = try {
                    args[0].toInt()
                } catch (e: NumberFormatException) {
                    printError("day argument must be an integer day number")
                    return
                }
                if(args[1] == "test") {
                    testOnly = true
                }
            }
        }

        if (day != 0) {
            val dayClass = getAllDayClasses()?.find { dayNumber(it.simpleName) == day }
            if (dayClass != null) {
                printDay(dayClass,testOnly)
            }
            else {
                printError("aoc.Day $day not found")
            }
        }
        else {
            val allDayClasses = getAllDayClasses()
            if (allDayClasses != null) {
                allDayClasses.sortedBy { dayNumber(it.simpleName) }.forEach { printDay(it,testOnly) }
            }
            else {
                printError("Couldn't find day classes - make sure you're in the right directory and try building again")
            }
        }
    }

    private fun getAllDayClasses(): MutableSet<Class<out Day>>? {
        return reflections.getSubTypesOf(Day::class.java)
    }

    private fun printDay(dayClass: Class<out Day>, testOnly: Boolean = false) {
        println("\n=== DAY ${dayNumber(dayClass.simpleName)} ===")
        val day = dayClass.constructors[0].newInstance() as Day

        val testInput = readInput(dayClass.simpleName + "_test")
        val checkResult1 = measureTimedValue { day.check1(testInput) }
        println("Check 1: ${if(checkResult1.value) "good" else "bad"}")
        val checkResult2 = measureTimedValue { day.check2(testInput) }
        println("Check 2: ${if(checkResult2.value) "good" else "bad"}")

        if (testOnly)
            return

        val realInput = readInput(dayClass.simpleName)
        val partOne = measureTimedValue { day.part1(realInput) }
        val partTwo = measureTimedValue { day.part2(realInput) }
        printParts(partOne, partTwo)
    }

    private fun printParts(partOne: TimedValue<Any>, partTwo: TimedValue<Any>) {
        val padding = max(partOne.value.toString().length, partTwo.value.toString().length) + 14        // 14 is 8 (length of 'Part 1: ') + 6 more
        println("Part 1: ${partOne.value}".padEnd(padding, ' ') + "(${partOne.duration})")
        println("Part 2: ${partTwo.value}".padEnd(padding, ' ') + "(${partTwo.duration})")
    }

    private fun printError(message: String) {
        System.err.println("\n=== ERROR ===\n$message")
    }

    private fun dayNumber(dayClassName: String) = dayClassName.replace("Day", "").replaceFirst("^0+(?!$)","").toInt()
}
