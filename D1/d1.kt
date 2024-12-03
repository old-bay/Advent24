// Old Bay
// Advent24
// Day 1

import java.io.File;
import kotlin.math.abs;

fun main() {
  
  println("Advent24 Day 1");

  // static file input, may be replaced with a prompt
  val input = "input.txt";

  // multable list vals for input, length initialised
  val list1 = mutableListOf<Int>();
  val list2 = mutableListOf<Int>();
  var length = 0;

  // input taken from file, length counted from lines
  // later lists will be static arrays
  File(input).forEachLine { line ->
    val (num1, num2) = line.split("   ").map { it.toInt() };
    list1.add(num1);
    list2.add(num2);
    length++;
  }
  
  // sort the lists from least to greatest
  list1.sort();
  list2.sort();

  // make new array of the least absolute differences
  val list = Array<Int>(length) { 0 };
  list.forEachIndexed { i, item ->
    list[i] = abs(list1[i] - list2[i]);
  }
  
  // pt1 sum of least absolute differences
  val diffSum = list.sum();
  println("List difference sum: $diffSum");  
  
  // loop to get similarity
  val similarity = Array<Int>(length) { 0 };
  similarity.forEachIndexed { i, item ->
    val count = list2.count { it == list1[i] }
    similarity[i] = list1[i] * count;
  }
  
  // p2 sum of similarity
  val simSum = similarity.sum();
  println("List similarity sum: $simSum");

}

