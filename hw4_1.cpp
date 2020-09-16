/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw4/challenges/matching-form/submissions/code/1321073369
 * Title: hw4_1
 * Abstract: This program takes a string of diffenet types of brackets as input and displays true if the each opening bracket
 * 			 has a corresponding closing bracket. Otherwise it displays false.
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
	string brackets;
	cin >> brackets;
	stack <int> s;
	for(int i=0; i<brackets.length(); i++){
		if(brackets[i] == '{' || brackets[i] == '[' || brackets[i] == '('){
			s.push(1);
		}else{
			s.pop();
		}
	}
	if(s.size() == 0){
		cout << "TRUE";
	}else{
		cout << "FALSE";
	}
}
