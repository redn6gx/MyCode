/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw6/challenges/connect-components/submissions/code/1321765471
 * Title:hw6_3
 * Abstract: This program does a bfs on each connected component and outputs the vertices that would connect all components with 
 *   		 the least amount of edges added.
 * Author:Bobby Davis
 * ID: 1806
 * Date: 3/13/2020
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

//reference: Dr. Byun's bfs method
//			 https://repl.it/@YBYUN/BFS
vector<int> bfs(vector<vector<int>> & graph, int src){
	queue<int> q;
	int count = 0;
	vector<int> mark(graph.size(), 0);
    
	mark[src] = ++count;
    q.push(src);
    while (!q.empty()) 
    {
        int curr = q.front();
        q.pop();

        for (int i = 0; i < graph[curr].size(); i++) 
        {
            int neighbor = graph[curr][i];
            if(mark[neighbor] == 0) 
            {
				mark[neighbor] = ++count;
                q.push(neighbor);
            }
        }
    }	
	return mark;
}

int main(int argc, char** argv) {	
	int vNum, eNum;
	cin >> vNum;
	cin >> eNum;
	vector<vector<int>> graph(vNum);
	for(int i=0; i<eNum; i++){
		int v1, v2;
		cin >> v1;
		cin >> v2;
		graph[v1].push_back(v2); 
	}
	
	vector<int> currMarked(graph.size(), 0);
	vector<int> temp;
	int curr = 0;
	int counter = 0;
	
	while(counter < vNum){
		for(int i=0; i<currMarked.size(); i++){
			if(currMarked[i] == 0){
				curr = i;
				break;
			}
		}
		temp = bfs(graph, curr);
		for(int i=0; i<temp.size(); i++){
			if(temp[i] != 0){
				currMarked[i] = temp[i];
				counter++;
			}
		}
	}
	
		int last = 0;
		bool otherConnectedComps = false;
		for(int i=1; i<currMarked.size(); i++){
			if(currMarked[i] == 1){
				cout << "Edge: " << last << "-" << i << "\n";
				last = i;
				otherConnectedComps = true;
			}
		}
	
	if(otherConnectedComps == false){
		cout << "No new edge:";
	}
		
	return 0;
}
