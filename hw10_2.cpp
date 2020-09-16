/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw10/challenges/closed-hashing/submissions/code/1323322076
 * Title:hw10_2
 * Abstract: This program simulates a hashtable implemented with linear probing. 
 * The initial size is set by the user. There are various commands that can be performed by the user as well.
 * Author:Bobby Davis
 * ID: 1806
 * Date: 5/2/2020
 * References: https://www.programiz.com/cpp-programming/examples/prime-number
 */

#include <iostream>
#include <array>
#include <vector>
#include <cmath>
#include <algorithm>
#include <iterator>
#include <sstream>
#include <string>
using namespace std;

//hash function: currInsert % tableSize
//load factor: current number of keys in table / tableSize

//https://www.programiz.com/cpp-programming/examples/prime-number
//I modified this into a function
int isPrime(int n){
  bool prime = true;
  for(int i = 2; i <= n / 2; ++i)
  {
      if(n % i == 0){
          prime = false;
          break;
      }
  }
  if (prime)
      return true;
  else
      return false;
}

int main(int argc, char** argv) {
	int tableSize;
	int commandNum;
	int keyCount = 0; //to keep track current total of keys in table
	double loadFactor; //threshhold will be 0.5
	
	cin >> tableSize;
	cin >> commandNum;
	
	vector<int> keyValues(tableSize);
	vector<string> state(tableSize);
	
	//initialize everything in state to empty and keyValues to 0
	for(int i=0; i<tableSize; i++){
		state[i] = "Empty";
		keyValues[i] = 0;
	}
	
	string command;
	int inputNum;
	int insertIndex;
	int searchIndex;
	int deleteIndex;
	int newSize;
	
	for(int w=0; w<commandNum; w++){
		cin >> command;
		
		if(command != "tableSize"){
			cin >> inputNum;
		}
		
		if(command == "insert"){
			insertIndex = inputNum % tableSize;
			int count = 0; //use this to prevent infinite loop
			bool flag = false;
			
			//check if space is taken. If it is find next empty slot
			if(state[insertIndex] == "Active"){
				do{
					//check if we reached end
					if(insertIndex == tableSize){
						insertIndex = 0;
					}else{ insertIndex++; }
					count++;
					//check if we have looped over everything
					if(count == tableSize){ 
						flag = true;
						break; 
					}
				}while(state[insertIndex] == "Active");
			}
			if(flag == false){
				keyValues[insertIndex] = inputNum;
				state[insertIndex] = "Active";
				keyCount++;
				
				//check load factor and adjust accordingly
				loadFactor = (keyCount + 0.0)/(tableSize + 0.0);
				if(loadFactor > 0.5){
					//calculate new size
					newSize = tableSize * 2;
					while(isPrime(newSize) == false){
						newSize++;
						if(isPrime(newSize)){
							break;
						}
					}
					//make temporary copies of old arrays
					int temp[tableSize];
					string temp2[tableSize];
					
					int k;
					
					for(k=0; k<tableSize; k++){
						temp[k] = keyValues[k];
					}
					
					for(k=0; k<tableSize; k++){
						temp2[k] = state[k];
					}
					
					//make new arrays
					keyValues.clear();
				    keyValues.resize(newSize);
				    
					state.clear();
				    state.resize(newSize);
				    
				   	for(k=0; k<newSize; k++){
						state[k] = "Empty";
						keyValues[k] = 0;
					}
				    
				    //place old values in appropriate positions
				    for(int i=0; i<tableSize; i++){
						insertIndex = temp[i] % newSize;
						keyValues[insertIndex] = temp[i];
						state[insertIndex] = temp2[i];
					}
					
					//set tableSize to newSize		
					tableSize = newSize;
				}
			}	
		}else if(command == "displayStatus"){
			searchIndex = inputNum % tableSize;
			if(keyValues[searchIndex] == 0){
				cout << state[searchIndex] <<endl;
			}else{
				cout << keyValues[searchIndex] << " " << state[searchIndex] <<endl;
			 }
		}else if(command == "tableSize"){
			cout << tableSize << endl;
		}else if(command == "search"){
			searchIndex = inputNum % tableSize;
			int pos = searchIndex;
			bool found = false;
			do{
				if(keyValues[pos] == inputNum){
					if(state[pos] == "Deleted"){
						break; //found should be false when be break here
					}
					cout << inputNum << " Found" << endl;
					found = true;
					break;
				}
				if(pos == tableSize){ //reached end of table so loop back
					pos = 0;
				}else{ pos++; }
			}while(pos != searchIndex);
			if(!found){
				 cout << inputNum << " Not found" << endl;
			}
		}else if(command =="delete"){
			deleteIndex = inputNum % tableSize;
			int pos = deleteIndex;
			bool found = false;
			do{
				if(keyValues[pos] == inputNum){	//found the value to delete
					state[pos] = "Deleted";
					keyCount--;
					loadFactor = (keyCount + 0.0)/(tableSize + 0.0);
					found = true;
					break;
				}
				if(pos == tableSize){ //reached end of table so loop back
					pos = 0;
				}else{ pos++; }
			}while(pos != deleteIndex);
			if(!found){
				 cout << inputNum << " Not found" << endl;
			}
		}else{ cout << "Command Not Found" << endl; }
	}
}
