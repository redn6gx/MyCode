/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw4/challenges/tsp-2-1/submissions/code/1321073184
 * Title: hw4_3
 * Abstract: This program takes a directed graph of cities as input and displays the shortest path to each city from
 *			 as a round trip from the destination city. The total cost of the minimum path is also shown. If no path
 *			 exists, then the cost is shown as -1;
 * Author: Bobby Davis
 * ID: 1806
 * Date: 2/21/2020
 */

#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include <map>
#include <algorithm>
#include <array>
using namespace std;

int main(int argc, char** argv) {

	int firstInputNum;

	//take first input
	cin >> firstInputNum;
	map<string, int> cityMap1; //key is city to represent num
	map<int, string> cityMap2; //key is digit to represent city
	int cityMatrix[firstInputNum][firstInputNum];
	
	//set everything in cityMatrix to 0
	for(int i=0; i<firstInputNum; i++){
		for(int j=0; j<firstInputNum; j++){
			cityMatrix[i][j] = 0;
		}
	}
	
	//get city names and store in cityMap1 and cityMap2
	for(int i=0; i<firstInputNum; i++){
		string temp = "";
		cin >> temp;
		cityMap1[temp] = i;
		cityMap2[i] = temp;
	}
	
	//take second input
	int secondInputNum;
	cin >> secondInputNum;
	
	//populate cityMatrix
	for(int i=0; i<secondInputNum; i++){
		string row, column;
		int weight;
		cin >> row >> column >> weight;
		cityMatrix[cityMap1.at(row)][cityMap1.at(column)] = weight;
	}
	
	//get all permutations of cities other than starting city
	int a[firstInputNum-1];
	for(int i=0; i<sizeof(a)/sizeof(*a); i++){
		a[i] = i+1; 
	}

	int n = sizeof(a)/sizeof(*a);
	int size = n+2; //size of tempArr
	int finalArr[size]; //this will be the permutation with the minimum weight
	int tempArr[size]; //holds the current permutation
	//set first and last index to 0 for starting city
	tempArr[0] = 0;
	tempArr[size-1] = 0;
	int min = 0; //holds the final cost of path
	bool flag = true; //flag to check for first time checking a valid perumutation
	// Find all possible permutations 
	//a contains a new permutation in each iteration
	do 
	{ 
		bool noPath = false;
		int tempMin = 0;
		
		//get a permutation
		for(int i=0; i<n; i++){
			tempArr[i+1]=a[i];
		}
		
		//get weight for current valid permutation
		for(int i=0; i<size-1; i++){
			int weight = cityMatrix[tempArr[i]][tempArr[i+1]];
			if(weight != 0){ //if cities are connected
				tempMin += weight;
			} else {
				noPath = true;
				break;
			  }
		}
		
		if(noPath == false){
			//set first weight we look at to min
			if(flag == true){
				min = tempMin;
				flag = false;
				for(int i=0; i<size; i++){
					finalArr[i] = tempArr[i];
				}
			}
			
			//check if current weight is less than min
			if(tempMin < min){
				min = tempMin;
				for(int i=0; i<size; i++){
					finalArr[i] = tempArr[i];
				}
			}
		}
	} while (next_permutation(a, a + n)); 
	
	//print output
	if(flag == true){ //if this is true then a path was never found
		cout << "Path:\n" << "Cost:-1";
	} else{
		cout << "Path:";
		for(int i=0; i<size; i++){
			cout << cityMap2.at(finalArr[i]);
			if(i != size-1){
				cout << "->";
			}
		}
		cout << endl;
		cout << "Cost:"<< min;
	  }
}
