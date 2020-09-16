/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw4/challenges/partitioning-numbers/submissions/code/1321073736
 * Title: hw4_2
 * Abstract: This program takes a series of integers and uses 2 different methods to sort them where the negative numbers are all 
 * 			 in front of the positive numbers. The outcomes of both methods are displayed.
 * Author: Bobby Davis
 * ID: 1806
 * Date: 2/21/2020
 */

#include <iostream>
#include <array>
#include <vector>
#include <map>
#include <stack>
#include <cmath>
#include <algorithm>
#include <iterator>
#include <sstream>
#include <string>
using namespace std;

int main(int argc, char** argv) {
	int size;
	cin >> size;
	int arr1[size];
	int arr2[size];
	for(int i=0; i<size; i++){
		cin >> arr1[i];
		arr2[i] = arr1[i];
	}
	
	//first method (beggining and end as start points until pointers overlap)
	int i = 0;
	int k = size-1;
	for(i; i<k; i++){
		if(arr1[i] >= 0){
			for(k; k>i; k--){
				if(arr1[k] < 0){
					//make swap
					int temp = arr1[i];
					arr1[i] = arr1[k];
					arr1[k] = temp;
					break;
				}
			}
		}
	}
	
	//display first result
	for(int i=0; i<size; i++){
		cout << arr1[i] << " ";
	}
	cout <<endl;
	
	//second method (first pointer find first positive, second searches everything after for a negative)
	i = 0;
	for(i; i<size-1; i++){
		if(arr2[i] >= 0){
			for(k=i+1; k<size; k++){
				if(arr2[k] < 0){
					//make swap
					int temp = arr2[i];
						arr2[i] = arr2[k];
						arr2[k] = temp;
						break;
				}
			}
		}
	}
	
	//display first result
	for(int i=0; i<size; i++){
		cout << arr2[i] << " ";
	}
	
}
