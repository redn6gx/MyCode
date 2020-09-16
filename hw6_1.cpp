/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw6/challenges/divide-and-conquer-max/submissions/code/1321731405
 * Title:hw6_1
 * Abstract: This program uses a recursive divide and conqure method to find the max value in a given array of ints.
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

// Reference: Dr. Byun's introduction to divide and conqure document 
//			  https://docs.google.com/document/d/1FWvcseQmnDcmwScnxDtxA5iMwfX71NuekA199FQoSEw/edit?pli=1
// Sum the array A from start index to end index
int max_div_N_conq(int A[ ], int start, int end) {
    if (start == end) {
        return A[start];
    }
    else {
        int max1 = max_div_N_conq (A, start, (start+end)/2);
        int max2 = max_div_N_conq (A, (start+end)/2+1, end);
        if(max1 > max2){
        	return max1;
		}else{
			return max2;	
		}
    }
}


int main(int argc, char** argv) {
	//take in input
	int size;
	cin >> size;
	int array[size];
	for(int i=0; i<size; i++){
		int temp;
		cin >> temp;
		array[i] = temp;
	}
	cout << max_div_N_conq(array, 0, size-1);
	return 0;
}
