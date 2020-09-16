/*
 * HackerRank link:https://www.hackerrank.com/contests/cst370-s20-hw1/challenges/common-nums/submissions/code/1319985248
 * Title:hw1_1
 * Abstract:This program compares 2 groups of numbers and displays all the matching numbers from both groups.
 * Author:Bobby Davis
 * ID:1806
 * Date:1/27/2020
*/

#include <iostream>
#include <vector>
#include <algorithm>

int main() {
  int groupNum = 0;
  int temp = 0;
  std::vector<int> arr1;
  std::vector<int> arr2;
  std::vector<int> matches;

std::cin >> groupNum;
for(int i = 0; i < groupNum; i++) {
  std::cin >> temp;
  arr1.push_back(temp);
}

std::cin >> groupNum;
for(int i = 0; i < groupNum; i++) {
  std::cin >> temp;
  arr2.push_back(temp);
}

for(int i = 0; i < arr1.size(); i++) {
  for(int k = 0; k < arr2.size(); k++) {
    if( arr1[i] == arr2[k] && std::find(matches.begin(),    matches.end(), arr1[i]) == matches.end() ) { 
           matches.push_back(arr1[i]);
    }
  }
}

  if(matches.empty()){
    std::cout << "Answer:NONE";
  } else {
    std::sort(matches.begin(), matches.end());
    std::cout << "Answer:";
    for (const int& i : matches) { std::cout << i << " "; }
  }
  

}