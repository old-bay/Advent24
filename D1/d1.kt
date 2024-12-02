// Old Bay
// Advent24
// Day 1

import java.io.File;
import kotlin.math.abs;

fun main() {
  
  val fileName = "input.txt";

  val list1 = mutableListOf<Int>();
  val list2 = mutableListOf<Int>();

  File(fileName).forEachLine { line ->
    val (num1, num2) = line.split("   ").map { it.toInt() };
    list1.add(num1);
    list2.add(num2);
  }
  
  list1.sort();
  list2.sort();

  val list = mutableListOf<Int>();
  if(list1.size == list2.size);
    for(i in 0..(list1.size - 1)) {
      list.add(abs(list1[i]-list2[i]));
    }
  
  val diffSum = list.sum();
  println("List difference sum: $diffSum");  
  
  val similarity = mutableListOf<Int>();
  for(i in 0..(list1.size - 1)) {
    val count = list2.count { it == list1[i] }
    similarity.add(list1[i] * count);
  }
  
  val simSum = similarity.sum();
  println("List similarity sum: $simSum");
}
