// Problem Statement: Transform String
// Chef gives you two strings A and B. You can perform the following operation on string A as many times as you want:

// Remove character A[i] from string A at index i. This has a cost of i, which is the index of the element you are removing.
// Note: The cost is always equal to the index in the current string and not the original string. Refer to the sample test case for an example.
// You need to print the minimum cost of transforming string A to string B using the above operation. If it is not possible to transform string A to string B, print -1.

// Input Format:
// The first line of input will contain a single integer T, denoting the number of test cases.
// Each test case consists of 2 lines:
// The first line of each test case contains string A.
// The second line of each test case contains string B.
// Output Format:
// For each test case, output on a new line the minimum cost of transforming string A to string B. If it is not possible to transform string A to string B using the operation, print -1.

// Constraints:
// 1 ≤ T ≤ 2 x 10^5
// 1 ≤ |A| ≤ 2 x 10^5 (|A| refers to the length of string A)
// 1 ≤ |B| ≤ 2 x 10^5 (|B| refers to the length of string B)
// The sum of |A| and |B| over all test cases won't exceed 2 x 10^5.

// Sample Input:
// 3
// accd
// cd
// abcd
// ed
// aaaabbb
// aa

// Sample Output:
// 2
// -1
// 11

// Explanation:
// Test Case 1:
// Remove A[1] (the character a) with a cost of 1. Now A becomes ccd.
// Remove A[1] (the character c) with a cost of 1. Now A becomes cd, which is equal to B.
// Total cost is 2.

// Test Case 2:
// It is not possible to transform string A to string B.

// Test Case 3:
// Remove A[1] (the character a) with a cost of 1. Now A becomes aaabbb.
// Remove A[1] (the character a) with a cost of 1. Now A becomes aabbb.
// Remove A[3] (the character b) with a cost of 3. Now A becomes aabb.
// Remove A[3] (the character b) with a cost of 3. Now A becomes aab.
// Remove A[3] (the character b) with a cost of 3. Now A becomes aa, which is equal to B.
// Total cost is 11.


#include <bits/stdc++.h>
using namespace std;

int main() {
	int t;
	cin>>t;
	for(int k=0;k<t;k++){
	    string a,b;
	    cin>>a>>b;
	    long long cost = 0;
	    int j=0;
	    int i=0;
	    while(i<a.length() && j<b.length()){
	        if(a[i]==b[j]){
	            j++;
	        }
	        i++;
	    }
	    if(j!=b.length()){
	        cout<<"-1"<<endl;
	        continue;
	    }
	    j=a.length();
	    for(i=b.length()-1;i>=0;i--){
	        while(j>=1 && b[i]!=a[j-1]){
	            j--;
	        }
	        cost += j;
	        j--;
	    }
	    long long base = (long long)b.length()*a.length()+a.length()-b.length()-((long long)b.length()*(b.length()-1))/2;
	    cout<<base-cost<<endl;
	}
}
