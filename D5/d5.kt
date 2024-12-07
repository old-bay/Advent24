// Old Bay
// Advent24
// Day 5

import java.util.*;
import java.io.File;

fun main() {

  println("Advent24 Day 5");

  val rules: HashMap<Int, MutableList<Int>> = getRules("rules.txt");
  val rulesInv: HashMap<Int, MutableList<Int>> = getRulesInv("rules.txt");
  val updates: MutableList<Array<Int>> = getUpdates("updates.txt");

  val(pageSum: Int, sortedSum: Int) = validUpdatesPageSum(rules, rulesInv, updates);
  println("Sum of valid middle page numbers: $pageSum");
  println("Sum of updated middle page numbers: $sortedSum");
}

// function to return hashmap from rule file
fun getRules(rulesFilePath: String): HashMap<Int, MutableList<Int>> {
  val rules = HashMap<Int, MutableList<Int>>();

  File(rulesFilePath).forEachLine { line ->
    val (value: Int, key: Int) = line.split("|").map { it.toInt() }
    if(rules[key] != null) {
      rules[key]?.add(value);
    } else {
      rules[key] = mutableListOf<Int>();
      rules[key]?.add(value);
    }
  }

  return rules;
}

fun getRulesInv(rulesFilePath: String): HashMap<Int, MutableList<Int>> {
  val rules = HashMap<Int, MutableList<Int>>();

  File(rulesFilePath).forEachLine { line ->
    val (key: Int, value: Int) = line.split("|").map { it.toInt() }
    if(rules[key] != null) {
      rules[key]?.add(value);
    } else {
      rules[key] = mutableListOf<Int>();
      rules[key]?.add(value);
    }
  }

  return rules;
}


// function to return list of arrays of page numbers by line
fun getUpdates(updatesFilePath: String): MutableList<Array<Int>> {
  val updates = mutableListOf<Array<Int>>();

  File(updatesFilePath).forEachLine { line ->
    val update: Array<Int> = line.split(",").map { it.toInt() }.toTypedArray();
    updates.add(update);
  }

  return updates;
}

fun validUpdatesPageSum (
  rules: HashMap<Int, MutableList<Int>>,
  rulesInv: HashMap<Int, MutableList<Int>>,
  updates: MutableList<Array<Int>>
  ): Pair<Int, Int> {

  var pageSum: Int = 0;
  var sortedSum: Int = 0;
  updates.forEach { update ->

    val term: Int = update.size - 1;
    var sorted: Array<Int>? = update;

    var index: Int = 0;
    var ordered: Boolean = true;
    while(ordered && index < update.size) {
      update.forEachIndexed { i, key ->
        rules[key]?.forEach { value ->
          if(value in update.sliceArray(i..term)) {
            ordered = false;
            sorted = bruteForceSort(rules, update);
          }
        }
      }
      index++;
    }
    if(ordered) pageSum += update[term / 2] else sortedSum += sorted!![term / 2];
  }
  return Pair(pageSum, sortedSum);
}

fun bruteForceSort(rules: HashMap<Int, MutableList<Int>>, array: Array<Int>): Array<Int>? {
  val result = array.copyOf()

  // Helper function to enforce rules on the array
  fun enforceRules(): Boolean {
    var changed = false
    for ((key, values) in rules) {
      val keyIndex = result.indexOf(key)
      if (keyIndex != -1) {
        for (value in values) {
          val valueIndex = result.indexOf(value)
          if (valueIndex != -1 && valueIndex < keyIndex) {
            // Swap the elements
            result[valueIndex] = key
            result[keyIndex] = value
            changed = true
          }
        }
      }
    }
    return changed
  }

  // Iteratively enforce the rules
  var iterations = 0
  val maxIterations = result.size * result.size // Prevent infinite loops in case of conflicting rules
  while (enforceRules()) {
    iterations++
    if (iterations > maxIterations) return null // Return null if no valid sort is possible
  }

  return result
}


