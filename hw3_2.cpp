/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw3/challenges/equal-groups/submissions/code/1320798396
 * Title: hw3_2
 * Abstract: This program takes the number of elements in a set and the corresponding amount of elements in the set as input with no duplicates.
 *			 The program then checks if the set can be divided into 2 seperate sets with equal sums. If yes it displays the set with the lowest numbers.
 * Author: Bobby Davis
 * ID: 1806
 * Date: 2/14/20
 */

#include <iostream>
#include <vector>
#include <cmath>
#include <string>
using namespace std;

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

int main() {
	int valueCount;
	int inputValue;
	int sum = 0;
	vector<int> v;
	
	//take first input
	cin >> 	valueCount;
	//create array to store values
	int valueArray[valueCount];
	
	//take input for each value and store in valueArray
	for(int i=0; i<valueCount; i++){
		cin >> inputValue;
		valueArray[i] = inputValue;
		sum += valueArray[i];
	}
	
	//check if sum of all values is odd
	if(sum%2 == 1){
		cout << "Equal Set: 0";
		//end program
		return 0;
	}

	//the sum we are looking for
	int looking_for = sum/2;
	
		//create array to hold binary num  
		int size = pow(2, valueCount);
		string bArray[size];
	
		//add right amount of zeros to first index
		string appZ = "";
		while(appZ.length() < valueCount){
		    	appZ.append("0");
			}
		bArray[0] = appZ;
		
		//populate rest of bArray
		for(int i=1; i<size; i++){
			appZ = "";
		    bArray[i] = toBin(i);
		    while(appZ.length() < (valueCount - bArray[i].length()) ){
		    	appZ.append("0");
			}
			bArray[i] = appZ.append(bArray[i]);
		}
		
		
		
		//variable for the final set we will print
		string set = "";
		//variable to store the smallest int in set
		int min = 0;
		//flag variable to keep track of first time we set min
		bool flag2 = true;
		//search for matching set with lowest int in it
		for(int i=0; i<size; i++){
			int sum = 0;
			//variable to keep track of current min in current iteration
			int currMin;
			//flag variable to keep track of the first time we set currMin
			bool flag = true;
			int lastVal = 0;
			string currSet = "";
			for(int k=0; k<bArray[i].length(); k++){
				if(bArray[i][k] == '1'){
					sum += valueArray[k];
					//compare current value at k to last value we looked at to sort currSet in ascending order
					if(valueArray[k] > lastVal){
						//append to end of currSet
						currSet.append(to_string(valueArray[k]));
						currSet.append(" ");
					} else {
						//append to begging of currSet
						currSet = to_string(valueArray[k]).append(" ").append(currSet);
					  }
					//keep track of the last value we looked at  
					lastVal = valueArray[k];  
					
					if(flag == true){
						currMin = valueArray[k];
						flag = false;
					} else {
						if(valueArray[k] < currMin){
							currMin = valueArray[k];
						}
					  }
					  
					if(flag2 == true){
						min = currMin;
						flag2 = false;
					}  
				}
			}
			if(sum == looking_for){
				if(currMin < min){
					set = currSet;
					min = currMin;
				}
			}
			flag = true;
		}
		if(set.length() == 0){
			cout << "Equal Set: 0";
		} else {
			cout << "Equal Set: " << set;
		}
}
