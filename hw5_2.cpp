/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw5/challenges/bfs-hops/submissions/code/1321571771
 * Title:hw5_2
 * Abstract: This program uses a BFS with a queue and mark array to find the cities within a given amount of hops of a source city in a graph.
 * Author:Bobby Davis
 * ID: 1806
 * Date: 3/6/2020
 */

#include <iostream>
#include <array>
#include <vector>
#include <queue>
#include <map>
#include <stack>
#include <cmath>
#include <algorithm>
#include <iterator>
#include <sstream>
#include <string>
using namespace std;

void printGraph(vector<vector<int>> & g) 
{
    for (int i = 0; i < g.size(); i++) 
    {
        cout << i;
        for (int j = 0; j < g[i].size(); j++) 
        {
            cout << " -> " << g[i][j];
        }
        cout << endl;
    }
}

int main(int argc, char** argv) {
	//declare variables for input
	int vNum, eNum, hops;
	string vCity, eCities, sourceCity;

	//take in vertex input
	cin >> vNum;
	map<string, int> vMap1; //to retrieve row or column number by city
	map<int, string> vMap2; //to retrive city by row or column number
	vector<vector<int>> graph(vNum); //vector the size of vNum holding other vecotrs
	for(int i=0; i<vNum; i++){
		cin >> vCity;
		vMap1[vCity] = i;
		vMap2[i] = vCity;
	}
	
	//take in edge input
	cin >> eNum;
	for(int i=0; i<eNum; i++){
		string source, destination;
		cin >> source;
		cin >> destination;
		graph[vMap1.at(source)].push_back(vMap1.at(destination)); 
	}

	//take in starting city and hop info
	cin >> sourceCity;
	cin >> hops;
	
	queue<int> q;
	vector<int> mark(graph.size(), 0);
	q.push(vMap1.at(sourceCity));
	int level = 0;
	int queueCount = 0;
	bool flag = true;
	while(!q.empty()){
		if(queueCount == 0){
			level++;
			flag = true;
		  }
		int curr = q.front();
        q.pop();

        for (int i = 0; i < graph[curr].size(); i++) {
            int neighbor = graph[curr][i];
            if(mark[neighbor] == 0) {
				mark[neighbor] = level;
                q.push(neighbor);
	            if(flag == true){
	            	queueCount++;
				}  	
            }
        }
        if(queueCount > 0){
			queueCount--;
		} 
        flag = false;
	}
	
	vector<string> result;
	result.push_back(sourceCity);
	for(int i=0; i<mark.size(); i++){
		if(mark[i] <= hops && mark[i] != 0){
			result.push_back(vMap2.at(i));
		}
	}
	sort(result.begin(), result.end());
	for(int i=0; i<result.size(); i++){
		cout << result[i] << "\n";
	}
}
