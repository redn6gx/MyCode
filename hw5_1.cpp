/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw5/challenges/palindrome-check-10/submissions/code/1321572266
 * Title:hw5_1
 * Abstract: This program uses recursion to check if a string is a palindrome
 * Author:Bobby Davis
 * ID: 1806
 * Date: 3/6/2020
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

bool isPalindrome(string word){
	char beg = toupper(word[0]);
	char end = toupper(word[word.size()-1]);
	if(word.size() <= 1){	//base case
		return true;
	}
	else{ 
		string temp = word.substr(1, word.size()-2);
		bool flag = isPalindrome(temp);
		if(beg == end && flag == true){
			return true;
		}else{ return false; }
	}	
}

int main(int argc, char** argv) {
	string input;
	getline(cin, input);
	
	int count = 0;
	for(int i=0; i<input.size(); i++){
		if(!isalpha(input[i]) && !isdigit(input[i])){
			input.erase(i,1);
			i--;
		}
	}
	
	bool result = isPalindrome(input);
	if(result == 1){
		cout << "TRUE";
	} else { cout << "FALSE"; }
}
