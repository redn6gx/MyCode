/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw2/challenges/reverse-number-13/submissions/code/1320496003
 * Title: hw2_1
 * Abstract: This program takes an integer in as an  input and prints it out in reverse order.
 * Author: Bobby Davis
 * ID: 1806
 * Date: 2/7/2020
 */

#include <iostream>

int main() {

  //declare and initiallize variables
  int num = 0;
  int result = 0;

  //take in user input
  std::cin >> num;

  //create reversed number
  while(num > 0){
    int endNum = num % 10; //gives the last digit in num
    result = endNum + (result * 10); //multiply by 10 to push current result over to the left on position then append new endNum (trailing zeros will never be added)
    num = num/10; //remove the last digit from num
  }

  //print result
  std::cout << result;
}