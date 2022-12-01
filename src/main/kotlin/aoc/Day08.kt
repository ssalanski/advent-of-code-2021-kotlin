package aoc

class Day08 : Day(8) {

  private fun parseLine(line: String): Pair<List<Set<Char>>, List<Set<Char>>> {
    return line.split('|').map { it.trim().split(" ").map(String::toSet) }.run { first() to last() }
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
    val set1: Set<Char>
    val set2: Set<Char>
    val set3: Set<Char>
    val set4: Set<Char>
    val set5: Set<Char>
    val set6: Set<Char>
    val set7: Set<Char>
    val set8: Set<Char>
    val set9: Set<Char>
    val set0: Set<Char>

    observations.first { (it.size == 2) }.let { set1 = it }
    observations.first { (it.size == 3) }.let { set7 = it }
    observations.first { (it.size == 4) }.let { set4 = it }
    observations.first { (it.size == 7) }.let { set8 = it }
    val segmentC: Char
    val segmentE: Char
    observations.filter { it.size == 6 }.distinct().let { sixes ->
      assert(sixes.size==3) { "found ${sixes.size} segment sets of size 6, expecting 3"}
      sixes.filter { (it - set7 - set4).size == 1 }.let {
        assert(it.size==1) {"found ${it.size} six-length sets X st X - {7} - {4} = [g], was expecting only 1"}
        set9 = it[0]
      }
      sixes.filter { it != set9 }.let { zeroAndSix ->
        assert(zeroAndSix.size ==2) {"found ${zeroAndSix.size} six-length sets other than {9}, should have been {0}+{6}"}
        segmentE = (zeroAndSix[0] - set7 - set4 - set9).first()
        val setX = zeroAndSix[0]
        val setY = zeroAndSix[1]
        if( (setX - setY).intersect(set1).isEmpty() ) {
          set6 = setX
          set0 = setY
        } else {
          set0 = setX
          set6 = setY
        }
        segmentC = (set0-set6).first()
      }
    }

    observations.filter { it.size == 5 }.distinct().let { fives ->
      assert(fives.size == 3) { "found ${fives.size} segment sets of size 5, expecting just 3" }
      fives.filter{!it.contains(segmentC)}.let {
        assert(it.size==1)
        set5 = it[0]
      }
      fives.filter{it.contains(segmentE)}.let {
        assert(it.size==1)
        set2 = it[0]
      }
      fives.filter{it.contains(segmentC) && !it.contains(segmentE)}.let {
        assert(it.size==1)
        set3 = it[0]
      }
    }

    return mapOf(
      set1 to '1',
      set2 to '2',
      set3 to '3',
      set4 to '4',
      set5 to '5',
      set6 to '6',
      set7 to '7',
      set8 to '8',
      set9 to '9',
      set0 to '0'
    )
  }

  override fun check1(input: List<String>): Boolean {
    return (part1(input).also { println(it) } == 26)
  }

  override fun check2(input: List<String>): Boolean {
    return (part2(input) == 61229)
  }
}

fun <A, B> Map<A, B>.invert(): Map<B, A> = entries.associate { Pair(it.value, it.key) }