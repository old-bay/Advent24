import java.io.File
import java.io.IOException

fun main() {
  val rulesLines = File("rules.txt").readLines();
  val updatesLines = File("updates.txt").readLines();

  val rules = mutableMapOf<Int, MutableList<Int>>();
  val updates = mutableListOf<MutableList<Int>>();

  for(line in rulesLines) {
    val (x, y) = line.split("|").map { it.toInt() }
    rules.getOrPut(x) { mutableListOf() }.add(y);
  }
  for(line in updatesLines) {
    updates.add(line.split(",").map { it.toInt() }.toMutableList());
  }

  fun isSorted(update: List<Int>): Boolean {
    for (i in 1 until update.size) {
      val a = update[i - 1];
      val b = update[i];
      if(rules[b]?.contains(a) == true) {
        return false
      }
    }
    return true
  }

  fun sortUpdate(update: MutableList<Int>) {
    update.sortWith { a, b ->
      rules[a]?.contains(b)?.let { if (it) -1 else 1 } ?: 0
    }
  }

  fun middlePage(update: List<Int>): Int = update[update.size / 2]

  val sortedUpdates = updates.filter(::isSorted)
  println("Sum of middle pages: ${sortedUpdates.sumOf(::middlePage)}")

  val unsortedUpdates = updates.filterNot(::isSorted)
  unsortedUpdates.forEach(::sortUpdate)
  println("Sum of middle pages (after sorting): ${unsortedUpdates.sumOf(::middlePage)}")
}
