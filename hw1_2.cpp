/*
 * HackerRank link:https://www.hackerrank.com/contests/cst370-s20-hw1/challenges/max-events/submissions/code/1320044469
 * Title:hw1_2
 * Abstract:Given a group of events with start and end times, this program displays the maximum number of events that will take place at the same time.
 * Author:Bobby Davis
 * ID:1806
 * Date:1/28/20
 */

#include <iostream>
#include <array>

int main() {
  int eventNum = 0;
  int startTime = 0;
  int endTime = 0;
  int arr[24];

  //set everything to 0 in arr
  for(int i = 0; i < 24; i++) {
    arr[i] = 0;
  }

  //take input
  std::cin >> eventNum;
  for(int i = 0; i < eventNum; i++) {
    std::cin >> startTime >> endTime;
    for(int k = startTime-1; k <= endTime-1; k++) {
      arr[k] = arr[k] + 1;
    }
  }

int temp = arr[0]; 
for(int i = 1 ; i < 24; i++){
  if(arr[i] > temp) {
    temp = arr[i];
  }
}

std::cout << "Answer:" << temp << "\n";





// for(int i = 0; i < 24; i++) {
//   std::cout << arr[i];
// }


}