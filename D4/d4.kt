// Old Bay
// Advent24
// Day 4

import java.io.File;

fun main() {

    println("Advent24 Day 4");

    val data = mutableListOf<String>();
    data.addAll(File("input.txt").readLines());

    val xmas = xmasCount(data);
    val cross = crossCount(data);
    println("Instances of XMAS: $xmas");
    println("Instances of X-MAS: $cross");
}

// searches for XMAS
fun xmasCount(data: MutableList<String>): Int {
  var count = 0;
  for(y in data.indices) {
    for(x in data[y].indices) {
      for(dy in -1..1) { // thx cookie
        for(dx in -1..1) { // I mean it
          if(dy != 0 || dx != 0) {
            val newY = y + 3 * dy;
            val newX = x + 3 * dx;
            if(newY in data.indices && newX in data[newY].indices) {
              if (
                data[y][x] == 'X' && data[newY][newX] == 'S' &&
                data[y + dy][x + dx] == 'M' && data[y + 2 * dy][x + 2 * dx] == 'A'
              ) {
                count++;
              }
            }
          }
        }
      }
    }
  }
  return count;
}

// searches for X-MAS
fun crossCount(data: MutableList<String>): Int {
  var count = 0;
  for(y in data.indices) {
    for(x in data[y].indices) {
      if(y + 2 < data.size && x + 2 < data[y].length) { // validation
        if(data[y][x] == 'M') { // top left kills two birds with one stone
          if (
            data[y + 1][x + 1] == 'A' && data[y + 2][x + 2] == 'S' && (
              (data[y + 2][x] == 'M' && data[y][x + 2] == 'S') ||
              (data[y + 2][x] == 'S' && data[y][x + 2] == 'M')
            )
          ) {
            count++;
          }
        } else if(data[y][x] == 'S') { // top left -> fewer stepped cases
          if (
            data[y + 1][x + 1] == 'A' && data[y + 2][x + 2] == 'M' && (
              (data[y + 2][x] == 'S' && data[y][x + 2] == 'M') ||
              (data[y + 2][x] == 'M' && data[y][x + 2] == 'S')
            )
          ) {
            count++;
          }
        }
      }
    }
  }
  return count;
}
