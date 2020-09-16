/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw3/challenges/adjacency-list/submissions/code/1320669541
 * Title: hw3_3
 * Abstract: This program takes a number of vertices and edges as input. It then takes the pairs of vertices that are connected by edges in as input.
 *			 The input is then converted to adjacency matrix format and displayed in the console.
 * Author: Bobby Davis
 * ID: 1806
 * Date: 2/10/20
 */

#include <iostream>
using namespace std;

int main() {
  int vNum, eNum, a, b;
  cin >> vNum;
  cin >>eNum;
  int arr[vNum][vNum];

  //initialize everything in 2d Array to 0
  for(int i = 0; i < vNum; i++){
    for(int j = 0; j < vNum; j++){
      arr[i][j] = 0;
    }
  }

  //read the rest of the input pairs and insert
  for(int k = 0; k < eNum; k++){
    cin >> a >> b;
    arr[a][b] = 1;
  }

  //iterate through 2d array to find pairs with 1
  //i will point to row
  for(int i = 0; i < vNum; i++){
    cout << i;
    //j will point to column
    for(int j = 0; j < vNum; j++){
      if(arr[i][j] == 1){
        cout << "->" << j;
      }
    }
    cout << "\n";
  }


}
