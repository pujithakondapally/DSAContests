// 4
// 5
// 4 1 3 2 1
// 5
// 2 4 3 4 2
// 1
// 1
// 6
// 1 1 1 1 6 6 

// YES
// NO
// YES
// NO

#include <bits/stdc++.h>
using namespace std;

int main() {
	int t;
	cin>>t;
	for(int i=0;i<t;i++){
	    int n;
	    cin>>n;
	    int num;
	    vector<int> arr;
	    for(int j=0;j<n;j++){
	        cin>>num;
	        arr.push_back(num);
	    }
	   int flag=0;
	   sort(arr.begin(),arr.end());
	   for(int k=0;k<n;k++){
	       if(k+1-arr[k]<0){
	           flag=1;
	           break;
	       }
	   }
	   if(flag==0){
	       cout<<"YES"<<endl;
	   }
	   else{
	       cout<<"NO"<<endl;
	   }
	}

}