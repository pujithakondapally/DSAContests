// Cool Subsequences
// You are given an array A containing N positive integers.
// A subsequence of this array is called cool if the averages of all non-empty subsequences of this subsequence are present in the complement subsequence.
// The complement subsequence refers to the subsequence formed by the elements we didn't consider.

// Example
// Consider the array
// A = [8, 4, 2, 1, 3, 2, 5, 8, 2].

// A possible cool subsequence is [2, 2, 8].

// The complement subsequence is [8, 4, 1, 3, 5, 2].
// The non-empty subsequences of [2, 2, 8] are:
// [2] → average = 2
// [8] → average = 8
// [2,2] → average = 2
// [2,8] → average = (2+8)/2 = 5
// [2,2,8] → average = (2+2+8)/3 = 4
// All these values {2, 8, 2, 5, 4} exist in the complement subsequence, so [2,2,8] is cool.
// If no such subsequence exists, print -1.

// Input Format
// The first line contains a single integer T (1 ≤ T ≤ 100), the number of test cases.
// Each test case consists of:
// An integer N (1 ≤ N ≤ 200000), the length of the array.
// A line with N space-separated integers A₁, A₂, ..., Aₙ (1 ≤ Aᵢ ≤ 10⁷), the array elements.
// Output Format
// For each test case:

// If a cool subsequence exists, print two lines:
// An integer K (1 ≤ K < N), the length of the cool subsequence.
// K space-separated integers, the cool subsequence.
// If no cool subsequence exists, print -1.
// If multiple cool subsequences exist, any valid one is acceptable.

// Constraints
// 1 ≤ T ≤ 100
// 1 ≤ N ≤ 2 x 10⁵ (Sum of N over all test cases won't exceed 2 × 10⁵)
// 1 ≤ Aᵢ ≤ 10⁷


#include <bits/stdc++.h>
using namespace std;

int main() {
	int t;
	cin>>t;
	while(t--){
	    int n;
	    cin>>n;
	    vector<int> arr(n);
	    for(int i=0;i<n;i++){
	        cin>>arr[i];
	    }
	    if(n==1){
	        cout<<-1<<"\n";
	        continue;
	    }
	    unordered_map<int,int> freq;
	    for(int i=0;i<n;i++){
	        freq[arr[i]]++;
	    }
	    int cool = -1;
	    
	    for(auto &num:freq){
	        if(num.second>=2){
	            cool = num.first;
	            break;
	        }
	    }
	    if(cool==-1){
	        cout<<"-1"<<"\n";
	    }
	    else{
	        cout<<1<<"\n"<<cool<<"\n";
	    }
	}

}
