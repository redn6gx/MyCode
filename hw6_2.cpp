/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw6/challenges/knapsack-8/submissions/code/1321733578
 * Title:hw6_2
 * Abstract: This is a program that solves the knapsack problem by finding all possible combinations of items and outputting
 *			 the combination with the greatest value that is withing the given capacity;
 * Author:Bobby Davis
 * ID: 1806
 * Date: 3/12/2020
 */

#include <iostream>
#include <array>
#include <vector>
#include <map>
#include <stack>
#include <queue>
#include <cmath>
#include <algorithm>
#include <iterator>
#include <sstream>
#include <string>
using namespace std;

//could use a vector of vectors instead of map to account items with same weight

//function to convert decimal to binary
string toBin(int dec){
  string result = "";
  vector<int> v;

  //perform binary conversion
  while(dec > 0){
    int remainder = dec % 2;
    v.push_back(remainder);
    dec = dec / 2;
  }
  
	//reverse iterate through v and create binary strings
	for (auto it = v.rbegin(); it != v.rend(); it++) 
         result.append(to_string(*it));

  return result;
}

int main(int argc, char** argv) {
	int itemNum, capacity;
	cin >> itemNum;
	cin >> capacity;
	map<int, int> items; //(weight, value)
	int weightList[itemNum]; //holds each individaul weight
	for(int i=0; i<itemNum; i++){
		int weight, value;
		cin >> weight;
		cin >> value;
		items[weight] = value;
		weightList[i] = weight;
	}
	
		//create array to hold binary num  
		int size = pow(2, itemNum);
		string bArray[size];
		
		//add right amount of zeros to first index
		string appZ = "";
		while(appZ.length() < itemNum){
		    	appZ.append("0");
			}
		bArray[0] = appZ;
		
		//populate rest of bArray
		for(int i=1; i<size; i++){
			appZ = "";
		    bArray[i] = toBin(i);
		    while(appZ.length() < (itemNum - bArray[i].length()) ){
		    	appZ.append("0");
			}
			bArray[i] = appZ.append(bArray[i]);
		}

		int maxValue = 0;
		int maxWeight = 0;
		string finalItems= "";
		for(int i=0; i<size; i++){
			int currValue = 0;
			int currWeight = 0;
			string currItems = "";
			for(int k=0; k<bArray[i].size(); k++){
				if(bArray[i][k] == '1'){
					currValue += items.at(weightList[k]);
					currWeight += weightList[k];
					currItems.append(to_string(k+1));
					currItems.append(" ");
				}
			}
			if(currValue >= maxValue && currWeight <= capacity){
				maxValue = currValue;
				maxWeight = currWeight;
				finalItems = currItems;
			}
		}
		
		if(maxValue == 0){
			cout << "Items:" << "\nCapacity:" << "\nValue:";
		}else{cout << "Items:" << finalItems << "\nCapacity:" << maxWeight  << "\nValue:" << maxValue;}

	return 0;
}
