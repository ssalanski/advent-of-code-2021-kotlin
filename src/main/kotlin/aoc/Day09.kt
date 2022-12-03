package aoc

class Day09 : Day(9) {
  override fun part1(input: List<String>): Int {
    val heightMap = HeightMap(input.map{ it.map { it.digitToInt() }})

//    println("found ${localMinInMap.size} local minima")
//    localMinInMap.forEach { println(it) }

    return heightMap.getLowPoints().map { (r,c) -> heightMap[r][c] }.sumOf { it + 1 }
  }

  override fun part2(input: List<String>): Int {
    val heightMap = HeightMap(input.map { it.map { it.digitToInt() } })
    return heightMap.getLowPoints().map {
      heightMap.getBasinAround(it).size
    }
      .highest(3)
      .product()
  }

  override fun check1(input: List<String>): Boolean {
    return (part1(input) == 15)
  }

  override fun check2(input: List<String>): Boolean {
    return (part2(input) == 1134)
  }
}

private fun <E: Comparable<E>> Collection<E>.highest(n: Int): List<E> = sorted().takeLast(n)
private fun Iterable<Int>.product(): Int = reduce { acc, x -> acc * x }

class HeightMap(hm:List<List<Int>>) : List<List<Int>> by hm {
  private val width = this[0].size
  fun getLowPoints(): List<Pair<Int, Int>> {
    val localMinCoords = emptyList<Pair<Int,Int>>().toMutableList()
    forEachIndexed { rowidx, row ->
      val localMinInRow = getLocalMinIdx(row)
      localMinInRow.forEach { localMinColIdx ->
        val isFirstRow = rowidx == 0
        val isLastRow = rowidx == size-1
        fun prevRowHigher() = (this[rowidx-1][localMinColIdx] > row[localMinColIdx])
        fun nextRowHigher() = (this[rowidx+1][localMinColIdx] > row[localMinColIdx])
        if( (isFirstRow || prevRowHigher()) && (isLastRow || nextRowHigher())  ) {
          localMinCoords.add(Pair(rowidx,localMinColIdx))
        }
      }
    }
    return localMinCoords
  }

  fun getBasinAround(lowpoint: Pair<Int, Int>): Set<Pair<Int,Int>> {
    var prevSize = 0
    val basin = mutableSetOf(lowpoint)
    while(basin.size > prevSize) {
      prevSize = basin.size
      basin.toList().forEach { (r, c) ->
        if ((r > 0) && (this[r - 1][c] < 9)) {
          basin.add(Pair(r - 1, c))
        }
        if ((r < size - 1) && (this[r + 1][c] < 9)) {
          basin.add(Pair(r + 1, c))
        }
        if ((c < width - 1) && (this[r][c + 1] < 9)) {
          basin.add(Pair(r, c + 1))
        }
        if ((c > 0) && (this[r][c - 1] < 9)) {
          basin.add(Pair(r, c - 1))
        }
      }
    }
    return basin
  }

}

fun getLocalMinIdx(lst: List<Int>): List<Int> {
  val localMins = mutableListOf<Int>()
  if( lst[0] < lst[1] ) {
    localMins.add(0)
  }
  for(i in 1 until lst.size-1) {
    if (lst[i-1] > lst[i] && lst[i] < lst[i+1] ) {
      localMins.add(i)
    }
  }
  if( lst[lst.size-2] > lst[lst.size-1] ) {
    localMins.add(lst.size-1)
  }
  return localMins
}