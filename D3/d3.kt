// Old Bay
// Advent24
// Day 3

import java.io.File;
import java.util.regex.Pattern;

fun main() {

  println("Advent24 Day 3");

  // static file input, may be replaced with a prompt
  val filePath = "input.txt";
  val file = File(filePath);
  val input = file.readText();

  val pairProducts = extractProducts(input);
  val sum = pairProducts.sum();
  println("Sum of product of mul(X,Y) pairs: $sum");

  val dosnDonts = extractProductsDnD(input);
  val dndSum = dosnDonts.sum();
  println("Sum of products of mul(X,Y) pars following do(), before don't(): $dndSum");

}

// mathing all mul(X,Y) and geting array of products
fun extractProducts(text: String): Array<Int> {

  val regex = Regex("mul\\(\\d+\\,\\d+\\)");

  // uses static array for all after initial string gathering
  val matches = regex.findAll(text);
  val matchList = matches.map { it.value }.toList();
  val list = Array(matchList.size) {0};
  var breaker = 0;
  var x = 0;
  var y = 0;
  matchList.forEachIndexed { i, match ->
    breaker = match.indexOfFirst { it == ',' }
    x = match.substring(4, breaker).toInt();
    y = match.substring(breaker + 1, match.length - 1).toInt();
    list[i] = x * y;
  }
  return list;
}

// function for dos and donts
fun extractProductsDnD(text: String): MutableList<Int> {

  val regex = Regex("mul\\(\\d+\\,\\d+\\)|do\\(\\)|don't\\(\\)");

  val matches = regex.findAll(text);
  val matchList = matches.map { it.value }.toList();

  val list = mutableListOf<Int>();
  var breaker = 0;
  var x = 0;
  var y = 0;
  var goAhead = true;
  matchList.forEachIndexed { i, match ->

    // conditionally adds to a list if after a do, before a dont
    if(match.equals("don't()")) {
      goAhead = false;
    } else if(match.equals("do()")) {
      goAhead = true;
    } else {
      if(goAhead) {
        breaker = match.indexOfFirst { it == ',' }
        x = match.substring(4, breaker).toInt();
        y = match.substring(breaker + 1, match.length - 1).toInt();
        list.add(x * y);
      }
    }
  }
  return list;
}


