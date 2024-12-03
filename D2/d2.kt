// Old Bay
// Advent24
// Day 2

import java.io.File;
import kotlin.math.abs;
import kotlin.collections.*;

fun main() {

  println("Advent24 Day 2");

  // static file input, may be replaced with a prompt
  val input = "input.txt";

  // length initialised
  var safe = 0;
  var unsafe = 0;

  // input taken from file, length counted from lines
  // later lists will be static arrays
  File(input).forEachLine { line ->
    val list = line.split(" ").map { it.toInt() };

    val signs = Array<Char>(list.size - 1) { ' ' };
    var index = 1;
    var isSafe = true;
    while(isSafe && index < list.size) {

      // == 0 test, > 3 test, <= 3 test
      if(list[index] - list[index - 1] == 0) {
        isSafe = false;
      } else if(abs(list[index] - list[index - 1]) > 3) {
        isSafe = false;
      } else if(abs(list[index] - list[index - 1]) <= 3) {
        if(list[index] - list[index - 1] > 0) {
          signs[index - 1] = '+';
        } else {
          signs[index - 1] = '-';
        }
      }

      // sign test
      if(index > 1 && signs[index - 1] != signs[index - 2]) {
        isSafe = false;
      }

      index++;

    }

    if(isSafe) {
      safe++;
    }
  }

  println("Safe reports: $safe");

  safe = 0;
  File(input).forEachLine { line ->
    val list = line.split(" ").map { it.toInt() };
    var safeCount = 0


    list.forEachIndexed { i, item ->
      val dList = list.subList(0, i) + list.subList(i + 1, list.size);
      val signs = Array<Char>(list.size - 1) { ' ' };
      var index = 1;
      var isSafe = true;
      while(isSafe && index < dList.size) {

        // == 0 test, > 3 test, <= 3 test
        if(dList[index] - dList[index - 1] == 0) {
          isSafe = false;
        } else if(abs(dList[index] - dList[index - 1]) > 3) {
          isSafe = false;
        } else if(abs(dList[index] - dList[index - 1]) <= 3) {
          if(dList[index] - dList[index - 1] > 0) {
            signs[index - 1] = '+';
          } else {
            signs[index - 1] = '-';
          }
        }

        // sign test
        if(index > 1 && signs[index - 1] != signs[index - 2]) {
          isSafe = false;
        }

        index++;

      }

      if(isSafe) {
        safeCount++;
      }
    }

    if(safeCount > 0) {
      safe++;
    }
  }

  println("Safe reports (dampened): $safe");
}
