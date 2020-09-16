/*
 * HackerRank link: https://www.hackerrank.com/contests/cst370-s20-hw8/challenges/binary-tree-4/submissions/code/1322616242
 * Title:hw8_1
 * Abstract: This program takes in input for various commands that can be performed on a tree.
 *			  First the user gives a number for the root node. The user can then perform operations for:
 *					appending a node; 
 * 					deleteing the last node;
 * 					inorder, preorder, postorder and levelorder traversals; 
 * 					checking if the tree is a BST
 * Author:Bobby Davis
 * ID: 1806
 * Date: 4/10/2020
 * References: https://www.geeksforgeeks.org/level-order-tree-traversal/
 *			   https://repl.it/@YBYUN/InorderTraversal
 *			   
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

//global vector to use with inorderBST method
vector<int> v;

//https://repl.it/@YBYUN/InorderTraversal
struct Node 
{
	// Data part for a node. 
    int data;
    Node* left;
    Node* right;

    // Constructor for a new node.
    Node(int d) 
    {
        data = d;
        left = nullptr;
        right = nullptr;
    }
};

//https://repl.it/@YBYUN/InorderTraversal
Node* mkTree(int data, Node* left, Node* right) 
{
    Node* root = new Node(data);
    root->left = left;
    root->right = right;
    return root;
}

//https://repl.it/@YBYUN/InorderTraversal
void inorderBST(Node* root) 
{
    if (root != nullptr)  // Or simply "if (root)"
    {
        inorderBST(root->left);
        v.push_back(root->data);
        inorderBST(root->right);
    }
}

void inorder(Node* root) 
{
    if (root != nullptr)  // Or simply "if (root)"
    {
        inorder(root->left);
        cout << root->data << " ";
        inorder(root->right);
    }
}

void preorder(Node* root) 
{
    if (root != nullptr)  // Or simply "if (root)"
    {
        cout << root->data << " ";
        inorder(root->left);
        inorder(root->right);
    }
}

void postorder(Node* root) 
{
    if (root != nullptr)  // Or simply "if (root)"
    {
        inorder(root->left);
        inorder(root->right);
        cout << root->data << " ";
    }
}

//https://www.geeksforgeeks.org/level-order-tree-traversal/
int height(Node* node)  
{  
    if (node == NULL)  
        return -1;  
    else
    {  
        /* compute the height of each subtree */
        int lheight = height(node->left);  
        int rheight = height(node->right);  
  
        /* use the larger one */
        if (lheight > rheight)  
            return(lheight + 1);  
        else return(rheight + 1);  
    }  
}  

int main(int argc, char** argv) {
	
	//take input
	int root, commandNum;
	cin >> root;
	cin >> commandNum;
	cin.ignore();
	Node* n1 = new Node(root);	//mainNode
	
	vector<Node*> nodeList;
	nodeList.push_back(n1);	//n1->data
	int treeSize = 1;
	
	for(int k=0; k<commandNum; k++){
		string command = "";
		getline(cin, command);

		string appendCheck = "";
		string appendNum = "";
		if(command.size() >= 8){
			appendCheck = command.substr(0, 6);
			appendNum = command.substr(7);
		}
	
		if(command.compare("preOrder") == 0){
			preorder(n1);
			cout << "\n";
		}else if(command.compare("postOrder") == 0){
			postorder(n1);
			cout << "\n";
		}else if(command.compare("inOrder") == 0){
			inorder(n1);
			cout << "\n";
		}
		else if(command.compare("height") == 0){
			cout << height(n1) << endl;
		}else if(command.compare("levelOrder") == 0){
			for(int j=0; j<treeSize; j++){
				cout << nodeList[j]->data << " ";
			}
			cout << "\n";
		}else if(command.compare("deleteLastNode") == 0){
			int parentIndex = 0;
			if(treeSize == 1){
				n1 = nullptr;
				treeSize--;
			}else{	// if even amount of nodes
				parentIndex = treeSize/2;
				if(nodeList[parentIndex-1]->right != nullptr){
					nodeList[parentIndex-1]->right = nullptr;
				}else{
					nodeList[parentIndex-1]->left = nullptr;
				}
				treeSize--;
			}
		}else if(command.compare("isBST") == 0){
			inorderBST(n1);
			bool first = true;
			bool flag = false;
			int prev = 0;
			for (const int& p : v){
				if(first){
					prev = p;
					first = false;
				}else{
					if(prev >= p){
						cout << "false";
						flag = true;
						break;
					}
				}
				prev = p;
			}
			if(!flag){
				cout << "true";
			}
			v.clear();
			cout << "\n";
		}else if(appendCheck.compare("append") == 0){
			//find parent node
			int nextParentIndex = 0;	// index of the next parent node to append to in nodeList
			if(treeSize == 1){
				Node* tempNode = new Node(stoi(appendNum));
				nodeList[0]->left = tempNode;
				nodeList.push_back(tempNode);
				treeSize++;
			}else if(treeSize%2 == 0){	// if even amount of nodes
				nextParentIndex = treeSize/2;
				Node* tempNode = new Node(stoi(appendNum));
				nodeList[nextParentIndex-1]->right = tempNode;
				nodeList.push_back(tempNode);
				treeSize++;
			}else{	// if odd amount of nodes
				nextParentIndex = (treeSize/2) + 1;
				Node* tempNode = new Node(stoi(appendNum));
				nodeList[nextParentIndex-1]->left = tempNode;
				nodeList.push_back(tempNode);
				treeSize++;
			}
		}
	}
	return 0;
}
