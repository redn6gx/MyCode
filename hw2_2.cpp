/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw2/challenges/time-difference-2/submissions/code/1320497900
 * Title:hw2_2
 * Abstract:This program takes 2 time stamps in as input and displays the difference of the two times.
 * Author:Bobby Davis
 * ID:1806
 * Date:2/7/2020
 */

#include <iostream>
#include <string>

using namespace std;

int main() {


  string time1;
  string time2;
  int t1a, t1b, t1c, t2a, t2b, t2c;

  std::cin >> time1;
  std::cin >> time2;

  t1a = stoi(time1.substr(0,2));
  t1b = stoi(time1.substr(3,2));
  t1c = stoi(time1.substr(6,2));

  t2a = stoi(time2.substr(0,2));
  t2b = stoi(time2.substr(3,2));
  t2c = stoi(time2.substr(6,2));

  int h = t2a - t1a;
  int m = t2b - t1b;
  int s = t2c - t1c;

  if(h < 0){
   h += 24;      
  }
  if(m < 0){
   m = m + 60;
  }
  if(s < 0){
   s = s + 60; 
  }
  if(t1c > t2c){
   m -= 1;
  }
  if(t1b > t2b){
   h -= 1;
  }
  string finalH = to_string(h);
  string finalM = to_string(m);
  string finalS = to_string(s);

  if(finalH.length() < 2){
   finalH = "0" + finalH;   
  }
  if(finalM.length() < 2){
   finalM = "0" + finalM;   
  }
  if(finalS.length() < 2){
   finalS = "0" + finalS;   
  }

  string result = finalH + ":" + finalM + ":" + finalS;

  cout << result;

}
