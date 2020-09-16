/*
 * HackerRank link: No hackerrank link for this assignment.
 * Title:hw7_2
 * Abstract: This program take an int as an input and creates a list of numbers, that is the size of the input, using pointers.
 *			 The user is then prompted to choose if they want the list to be in ascending, descending, or random order.
 *			 Next the program performs a insertions sort, merge sort, and quick sort 3 times. 
 *			 The execution times for each sorting method is printed everytime.
 * Author:Bobby Davis
 * ID: 1806
 * Date: 3/26/2020
 * Citation Links: https://www.google.com/search?q=c%2B%2B+how+to+shuffle+array&rlz=1C1CHBF_enUS829US829&oq=c%2B%2B+how+to+shuffle+array&aqs=chrome..69i57j0l6.3445j0j7&sourceid=chrome&ie=UTF-8#kpvalbx=_-Uh9XryeJ7uu0PEPo_WBSA32, 
 *				   https://www.geeksforgeeks.org/insertion-sort/
 *				   http://www.cplusplus.com/doc/tutorial/dynamic/
 *				   https://www.geeksforgeeks.org/merge-sort/
 *				   https://www.geeksforgeeks.org/quick-sort/
 *				   https://www.geeksforgeeks.org/measure-execution-time-function-cpp/
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
#include <chrono> 
#include <iomanip> 
#include <bits/stdc++.h>
using namespace std::chrono;
using namespace std;

void insertionSort(int * container, int n)  
{  
    int i, key, j;  
    for (i = 1; i < n; i++) 
    {  
        key = container[i];  
        j = i - 1;  
  
        /* Move elements of arr[0..i-1], that are  
        greater than key, to one position ahead  
        of their current position */
        while (j >= 0 && container[j] > key) 
        {  
            container[j + 1] = container[j];  
            j = j - 1;  
        }  
        container[j + 1] = key;  
    }  
} 

void merge(int * container, int l, int m, int r) 
{ 
    int i, j, k; 
    int n1 = m - l + 1; 
    int n2 =  r - m; 
  
    /* create temp arrays */
//    int L[n1], R[n2]; 
    int * L;
    int * R;
	L = new int [n1];
	R = new int [n2];
  
    /* Copy data to temp arrays L[] and R[] */
    for (i = 0; i < n1; i++) 
        L[i] = container[l + i]; 
    for (j = 0; j < n2; j++) 
        R[j] = container[m + 1+ j]; 
  
    /* Merge the temp arrays back into arr[l..r]*/
    i = 0; // Initial index of first subarray 
    j = 0; // Initial index of second subarray 
    k = l; // Initial index of merged subarray 
    while (i < n1 && j < n2) 
    { 
        if (L[i] <= R[j]) 
        { 
            container[k] = L[i]; 
            i++; 
        } 
        else
        { 
            container[k] = R[j]; 
            j++; 
        } 
        k++; 
    } 
  
    /* Copy the remaining elements of L[], if there 
       are any */
    while (i < n1) 
    { 
        container[k] = L[i]; 
        i++; 
        k++; 
    } 
  
    /* Copy the remaining elements of R[], if there 
       are any */
    while (j < n2) 
    { 
        container[k] = R[j]; 
        j++; 
        k++; 
    } 
} 
  
/* l is for left index and r is right index of the 
   sub-array of arr to be sorted */
void mergeSort(int * container, int l, int r) 
{ 
    if (l < r) 
    { 
        // Same as (l+r)/2, but avoids overflow for 
        // large l and h 
        int m = l+(r-l)/2; 
  
        // Sort first and second halves 
        mergeSort(container, l, m); 
        mergeSort(container, m+1, r); 
  
        merge(container, l, m, r); 
    } 
} 

void swap(int* a, int* b)  
{  
    int t = *a;  
    *a = *b;  
    *b = t;  
}  
  
/* This function takes last element as pivot, places  
the pivot element at its correct position in sorted  
array, and places all smaller (smaller than pivot)  
to left of pivot and all greater elements to right  
of pivot */
int partition (int * container, int low, int high)  
{  
    int pivot = container[high]; // pivot  
    int i = (low - 1); // Index of smaller element  
  
    for (int j = low; j <= high - 1; j++)  
    {  
        // If current element is smaller than the pivot  
        if (container[j] < pivot)  
        {  
            i++; // increment index of smaller element  
            swap(&container[i], &container[j]);  
        }  
    }  
    swap(&container[i + 1], &container[high]);  
    return (i + 1);  
}  
  
/* The main function that implements QuickSort  
arr[] --> Array to be sorted,  
low --> Starting index,  
high --> Ending index */
void quickSort(int * container, int low, int high)  
{  
    if (low < high)  
    {  
        /* pi is partitioning index, arr[p] is now  
        at right place */
        int pi = partition(container, low, high);  
  
        // Separately sort elements before  
        // partition and after partition  
        quickSort(container, low, pi - 1);  
        quickSort(container, pi + 1, high);  
    }  
}  

void copyContainer(int * container, int * copy, int inSize){
	//create a copy of container
	for(int i=0; i<inSize; i++){
		copy[i] = container[i];
	}
}

int main(int argc, char** argv) {
	int inSize, order;
	string choice;
	
	cout << "Enter input size: ";
	cin >> inSize;
	
	int * container;
	container = new int [inSize];
	
	do{
		cout << "========== Select Order of Input Numbers ==========\n";
		cout << "     1. Ascending Order\n     2. Descending Order\n     3. Random Order\n";
		cout << "Choose order: ";
		cin >> order;
		if(order == 1){	
			cout << "============================================================\n";
			cout << "Generate input data in " << choice << " . . .";
			
			choice = "ascending order";
			for(int i=0; i<inSize; i++){
				container[i] = i+1;
			}
		}
		else if(order == 2){
			cout << "============================================================\n";
			cout << "Generate input data in " << choice << " . . .";
			
			choice = "descending order";
			int tempSize = inSize;
			for(int i=0; i<inSize; i++){
				container[i] = tempSize;
				tempSize--;
			}
		}
		else if(order == 3){
			cout << "============================================================\n";
			cout << "Generate input data in " << choice << " . . .";
			
			//create container sorted in ascending order
			choice = "random order";
			for(int i=0; i<inSize; i++){
				container[i] = i+1;
			}
			
			//shuffle container
			int random =0;
			int temp = 0;
			for(int i=0; i<inSize; i++){
				random = rand() % inSize;
				temp = container[i];
				container[i] = container[random];
				container[random] = temp;
			}
		}
		else{cout << "\nINPUT ERROR: INVALID CHOICE\n\n";}
	}while(order != 1 && order != 2 && order != 3);

	cout << "\nDone.\n============================================================\n\n";
	
	int * copy;
	copy = new int [inSize];
	
	auto start = high_resolution_clock::now();
	auto stop = high_resolution_clock::now();
	std::chrono::duration<float> duration = stop - start;
	
	float insertSum = 0;
	float mergeSum = 0;
	float quickSum = 0;
	float time = 0;

	cout << "========================== 1st Run =========================\n";
	
	copyContainer(container, copy, inSize);
	start = high_resolution_clock::now();
	insertionSort(copy, inSize);
	stop = high_resolution_clock::now();
	duration = duration_cast<milliseconds>(stop - start);
	time = duration.count();
	insertSum += time;
	cout << "Insertion sort: " << fixed << setprecision(6) << duration.count() << " milliseconds" << endl;
	
	copyContainer(container, copy, inSize);
	start = high_resolution_clock::now();
	mergeSort(copy, 0, inSize-1); 
	stop = high_resolution_clock::now();
	duration = duration_cast<milliseconds>(stop - start);
	time = duration.count();
	mergeSum += time;
	cout << "Merge sort: " << fixed << setprecision(6) << duration.count() << " milliseconds" << endl;
	
	copyContainer(container, copy, inSize);
	start = high_resolution_clock::now();
	quickSort(copy, 0, inSize-1);
	stop = high_resolution_clock::now();
	duration = duration_cast<milliseconds>(stop - start);
	time = duration.count();
	quickSum += time;
	cout << "Quick sort: " << fixed << setprecision(6) << duration.count() << " milliseconds" << endl;
	
	cout << "========================== 2nd Run =========================\n";
	
	copyContainer(container, copy, inSize);
	start = high_resolution_clock::now();
	insertionSort(copy, inSize);
	stop = high_resolution_clock::now();
	duration = duration_cast<milliseconds>(stop - start);
	time = duration.count();
	insertSum += time;
	cout << "Insertion sort: " << fixed << setprecision(6) << duration.count() << " milliseconds" << endl;
	
	copyContainer(container, copy, inSize);
	start = high_resolution_clock::now();
	mergeSort(copy, 0, inSize-1); 
	stop = high_resolution_clock::now();
	duration = duration_cast<milliseconds>(stop - start);
	time = duration.count();
	mergeSum += time;
	cout << "Merge sort: " << fixed << setprecision(6) << duration.count() << " milliseconds" << endl;
	
	copyContainer(container, copy, inSize);
	start = high_resolution_clock::now();
	quickSort(copy, 0, inSize-1);
	stop = high_resolution_clock::now();
	duration = duration_cast<milliseconds>(stop - start);
	time = duration.count();
	quickSum += time;
	cout << "Quick sort: " << fixed << setprecision(6) << duration.count() << " milliseconds" << endl;
	
	cout << "========================== 3rd Run =========================\n";

	copyContainer(container, copy, inSize);
	start = high_resolution_clock::now();
	insertionSort(copy, inSize);
	stop = high_resolution_clock::now();
	duration = duration_cast<milliseconds>(stop - start);
	time = duration.count();
	insertSum += time;
	cout << "Insertion sort: " << fixed << setprecision(6) << duration.count() << " milliseconds" << endl;
	
	copyContainer(container, copy, inSize);
	start = high_resolution_clock::now();
	mergeSort(copy, 0, inSize-1); 
	stop = high_resolution_clock::now();
	duration = duration_cast<milliseconds>(stop - start);
	time = duration.count();
	mergeSum += time;
	cout << "Merge sort: " << fixed << setprecision(6) << duration.count() << " milliseconds" << endl;
	
	copyContainer(container, copy, inSize);
	start = high_resolution_clock::now();
	quickSort(copy, 0, inSize-1);
	stop = high_resolution_clock::now();
	duration = duration_cast<milliseconds>(stop - start);
	time = duration.count();
	quickSum += time;
	cout << "Quick sort: " << fixed << setprecision(6) << duration.count() << " milliseconds" << endl;


	cout << "========================== Ranking =========================\n";
	float insertResult = insertSum/3.0;
	float mergeResult = mergeSum/3.0;
	float quickResult = quickSum/3.0;
	
	int counter = 1;
	if(insertResult > mergeResult){
		counter++;
	}
	if(insertResult > quickResult){
		counter++;
	}
	int insertPos = counter;
	counter = 1;
	if(mergeResult > insertResult){
		counter++;
	}
	if(mergeResult > quickResult){
		counter++;
	}
	int mergePos = counter;
	counter = 1;
	if(quickResult > insertResult){
		counter++;
	}
	if(quickResult > mergeResult){
		counter++;
	}
	int quickPos = counter;
	
	string ranks[3];
	ranks[insertPos-1] = "Insertion sort";
	ranks[mergePos-1] = "Merge sort";
	ranks[quickPos-1] = "Quick sort";
	int curr = 1;
	for(int i=0; i<3; i++){
		cout << "(" << curr << ") " << ranks[i] << endl;
		curr++;
	}
	
}
