package aoc

class Day08 : Day(8) {

  private fun parseLine(line: String): Pair<List<Set<Char>>, List<Set<Char>>> {
    return line.split('|').map { it.split(" ").map(String::toSet) }.run { first() to last() }
  }

  override fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      val (observed, asdf) = parseLine(line)
      countUnique(asdf)
    }
  }

  private fun countUnique(asdf: List<Set<Char>>) : Int {
    return asdf.count { (it.size == 2) or (it.size == 3) or (it.size == 4) or (it.size == 7) }
  }
  private fun ahhh(line: String): Int {
    val (observed, signal) = parseLine(line)
    val mapping: Map<Set<Char>, Char> = deduceMapping(observed)
    return signal.map { mapping.getOrDefault(it,"z") }.joinToString("").toInt()
  }

  override fun part2(input: List<String>): Int {
    val zxcv = input.map { line ->
      ahhh(line)
    }
    return zxcv.sum()
  }

  private fun deduceMapping(observations: List<Set<Char>>): Map<Set<Char>,Char> {
//    val mapping = mutableMapOf<Set<Char>,Char>()
    val invMapping = mutableMapOf<Char,Set<Char>>()
    observations.first { (it.size == 2) }.let { invMapping['1'] = it } //mapping[it] = '1' }
    observations.first { (it.size == 3) }.let { invMapping['7'] = it } //mapping[it] = '7' }
    observations.first { (it.size == 4) }.let { invMapping['4'] = it } //mapping[it] = '4' }
    observations.first { (it.size == 7) }.let { invMapping['8'] = it } //mapping[it] = '8' }
    val segmentC: Char
    val segmentE: Char
    observations.filter { it.size == 6 }.distinct().let { sixes ->
      assert(sixes.size==2) { "found ${sixes.size} segment sets of size 6, expecting just 2"}
      val set1 = sixes[0]
      val set2 = sixes[1]
      val difference = (set1 - set2)
      assert(difference.size == 1)
      val missingChar = difference.first()
      val otherDifference = (set1 - set2)
      assert(otherDifference.size == 1)
      val otherMissingChar = difference.first()
      if(invMapping['1']?.contains(missingChar) == true) {
        invMapping['9'] = set1
        invMapping['6'] = set2
        segmentC = missingChar
        segmentE = otherMissingChar
      } else {
        invMapping['6'] = set1
        invMapping['9'] = set2
        segmentE = missingChar
        segmentC = otherMissingChar
      }
    }
    observations.filter { it.size == 5 }.distinct().let { fives ->
      assert(fives.size == 3) { "found ${fives.size} segment sets of size 5, expecting just 3" }
      fives.filter{!it.contains(segmentC)}.let {
        assert(it.size==1)
        invMapping['5'] = it[0]
      }
      fives.filter{it.contains(segmentE)}.let {
        assert(it.size==1)
        invMapping['2'] = it[0]
      }
      fives.filter{it.contains(segmentC) && !it.contains(segmentE)}.let {
        assert(it.size==1)
        invMapping['3'] = it[0]
      }
    }

    return invMapping.invert()
  }

  override fun check1(input: List<String>): Boolean {
    return (part1(input).also { println(it) } == 26)
  }

  override fun check2(input: List<String>): Boolean {
    return (part2(input) == 61229)
  }
}

fun <A, B> Map<A, B>.invert(): Map<B, A> = entries.associate { Pair(it.value, it.key) }