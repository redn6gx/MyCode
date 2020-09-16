/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw3/challenges/binary-numbs/submissions/code/1320793560
 * Title: hw3_1
 * Abstract: This program takes a number of elements and the corresponding amount of elements in as input. The total amount of binary
 *			 combinations and each possible subset is the displayed in the console;
 * Author: Bobby Davis
 * ID: 1806
 * Date: 2/12/20
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

  int eNum;
  string element;

  //take input and store elements
  cin >> eNum;
  string eArray[eNum];
  
  if(eNum == 0){
  	cout << "0:EMPTY";
  } else {
  
  
	  for(int i = 0; i < eNum; i++){
	    cin >> element;
	    eArray[i] = element;
	  }
	  
		//create array to hold binary num  
		int size = pow(2, eNum);
		string bArray[size];
		
		
		//add right amount of zeros to first index
		string appZ = "";
		while(appZ.length() < eNum){
		    	appZ.append("0");
			}
		bArray[0] = appZ;
		
		//populate rest of bArray
		for(int i=1; i<size; i++){
			appZ = "";
		    bArray[i] = toBin(i);
		    while(appZ.length() < (eNum - bArray[i].length()) ){
		    	appZ.append("0");
			}
			bArray[i] = appZ.append(bArray[i]);
		}
		
		//create final output strings
		for(int i=0; i<size; i++){
			string result = ":";
			for(int k=0; k<bArray[i].length(); k++){
				if(bArray[i][k] == '1'){
					result.append(eArray[k]);
					result.append(" ");
				}
			}
			//print final strings
			if(result.length() > 1){
			cout << bArray[i] << result << "\n";
			} else{
				cout << bArray[i] << ":EMPTY" << "\n";		
			  }
		}

}

}
