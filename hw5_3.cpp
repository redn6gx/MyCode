/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw5/challenges/dfs-4/submissions/code/1321574581
 * Title:hw5_3
 * Abstract: This program takes in a graph and uses a DFS with a stack to return a mark array
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

int main(int argc, char** argv) {
	
	//take in input for vertices and edges
	int vNum, eNum;
	cin >> vNum;
	cin >> eNum;
	
	//2D array connected with vertices
	int graph[vNum][vNum];
	//fill with zeroes
	for(int i=0; i<vNum; i++){
		for(int k=0; k<vNum; k++){
			graph[i][k] = 0;
		}
	}
	//take input for source and destination
	int source, destination;
	for(int i=0; i<eNum; i++){
		cin >> source;
		cin >> destination;
		//populate 2D array
		graph[source][destination] = 1;
	}
	
	stack<int> s;	//keeps track of rows visited
	int mark[vNum];	//keeps track of order of visited vertices
	int counts[vNum];	//array to keep track of columns visited in each row
	//fill mark and counts with zeroes
	for(int i=0; i<vNum; i++){
		counts[i] = 0;
		mark[i] = 0;
	}
	int count = 2;	//keeps track of vertex order for mark array
	int curr = 0;

	while(counts[0] < vNum){
		for(int k=counts[curr]; k<vNum; k++){
			if(graph[curr][k] == 1 && mark[k] == 0){
				s.push(curr);
				counts[curr] = counts[curr] + 1;
				mark[k] = count;
				count++;
				curr = k;
				break;
			}else {
				counts[curr] = counts[curr] + 1;
			}
		}
		if(counts[curr] == vNum){
			s.pop();
			if(!s.empty()){
				curr = s.top();
			}
		}
	}

	mark[0] = 1;
	for(int i=0; i<vNum; i++){
		cout << "Mark[" << i << "]:" << mark[i] << "\n";
	}
	
}
