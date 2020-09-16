/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw8/challenges/kahns-topological-sort/submissions/code/1322618849
 * Title:hw8_2
 * Abstract: Performs a topological search using source removal
 * Author:Bobby Davis
 * ID: 1806
 * Date: 4/10/2020
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

struct Node {
	// Data part for a node. 
    int data;
    Node* next;

    // Constructor for a new node.
    Node(int d) {
        data = d;
        next = nullptr;
    }
};

int main(int argc, char** argv) {
	int vNum, eNum;
	cin >> vNum;
	cin >> eNum;

	int graph[vNum][vNum] = {0};	//create 2d array with all 0's
	int inDegree[vNum] = {0};	//create array with all 0's
	int source, destination;
	
	//create graph
	for(int i=0; i<eNum; i++){
		cin >> source;
		cin >> destination;
		graph[source][destination] = 1;
	}
	
	//create inDegree array
	for(int i=0; i<vNum; i++){
		for(int k=0; k<vNum; k++){
			if(graph[i][k] == 1){
				inDegree[k]++;
			}
		}
	}
	
	//create queue
	queue <int> q;
	bool foundZero = false;
	
	//print inDegree array
	for(int i=0; i<vNum; i++){
		cout << "In-degree[" << i << "]:" << inDegree[i] << "\n";
		if(inDegree[i] == 0){
			q.push(i);
			foundZero = true;
		}
	}
	
	if(foundZero){
		int curr = q.front();
		cout << "Order:";
		
		while(!q.empty()){
			for(int i=0; i<vNum; i++){
				if(graph[curr][i] == 1){
					inDegree[i]--;
					if(inDegree[i] == 0){
						q.push(i);
					}
				}
			}
			cout << q.front();
			if(q.size() != 1){
				cout << "->";
			}
			q.pop();
			if(!q.empty()){
				curr = q.front();
			}
		}
	}else{ cout << "No Order:"; }
	
	
	return 0;
}
