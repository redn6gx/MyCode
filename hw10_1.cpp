/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw10/challenges/radix-sort-4-1/submissions/code/1323325531
 * Title:hw10_1
 * Abstract: This program implements radix sort with counting sort as a sub-routine. 
 * The user enters how many integers they want to sort followed by the actual numbers.
 * Author:Bobby Davis
 * ID: 1806
 * Date: 5/2/2020
 */

#include <iostream>
#include <array>
#include <cmath>
#include <algorithm>
#include <iterator>
#include <sstream>
#include <string>
using namespace std;

int countDigits(int num){
	int count = 0;
	while(num > 0){
		if(num > 0){
			count++;
		}
		num = num/10;
	}
	return count;
}

int main(int argc, char** argv) {
	int elementNum;
	cin >> elementNum;
	string elementArray[elementNum];
	
	//populate array with user input
	int currElement;
	for(int i=0; i<elementNum; i++){
		cin >> currElement;
		elementArray[i] = to_string(currElement);
	}
	
	//find the max value
	int max = 0;
	for(int i=0; i<elementNum; i++){
		if(i == 0){	//assume first value is max
			max = stoi(elementArray[i]);
		}else{
			if(stoi(elementArray[i]) > max){
				max = stoi(elementArray[i]);
			}
		}
	}
	
	//find how many digits are in max
	int digits = countDigits(max);
	
	//change all numbers in elementArray to have the same amount of digits as max
	for(int i=0; i<elementNum; i++){
		int currDigits = countDigits(stoi(elementArray[i]));
		while(currDigits < digits){
			elementArray[i] = "0" + elementArray[i];
			currDigits++;
		}
	}

//	counting sort
	string sortedArray[elementNum];
	int pos = digits-1;
	while(pos >= 0){
		//array to store count of occurences for each elements current digit
		int frequenceyArray[10] = {0};
		for(int i=0; i<elementNum; i++){
			frequenceyArray[int(elementArray[i].at(pos)) - 48] += 1; //**********
		}
		
		//do cumalative sum from left to right
		for(int i=1; i<=9; i++){
			frequenceyArray[i] += frequenceyArray[i-1];
		}
		
		//shift everything in frequenceyArray over one to the right
		for(int i=9; i>=0; i--){
			if(i==0){
				frequenceyArray[i] = 0;
			}else{
				frequenceyArray[i] = frequenceyArray[i-1];
			}
		}

		//itereate through elementArray and place numbers in corresponding index from frequenceyArray into sortedArray
		for(int i=0; i<elementNum; i++){
			sortedArray[frequenceyArray[int(elementArray[i].at(pos)) - 48]] = elementArray[i];
			frequenceyArray[int(elementArray[i].at(pos)) - 48] += 1;
		}
		
		for(int i=0; i<elementNum; i++){
			elementArray[i] = sortedArray[i];
		}
		
		//print each step of radix sort
		for(int i=0; i<elementNum; i++){
			cout << stoi(sortedArray[i]) << " ";
		}
		cout << "\n";
		
		//change position to next most significant digit
		pos--;
	}
}
