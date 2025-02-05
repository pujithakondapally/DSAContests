// input
// 3
// 5 3
// 1 2 1 4 2
// 3 10
// 2 5 3
// 4 5
// 90 2 5 6

// output
// 4
// 3
// 3

// modify one element by multiplying it with given value of k to obtain the maximum non decreasing sequence.

#include <bits/stdc++.h>
using namespace std;

int main() {
	int t;
	cin>>t;
	for(int i=0;i<t;i++){
	    int n,k;
	    cin>>n>>k;
	    int maxi = 1;
	    vector<int> v(n);
	    for(int x=0;x<n;x++){
	        cin>>v[x];
	    }
	    int local_max=1,count=1;
	    for(int y=1;y<n;y++){
            if(v[y-1]<=v[y])
                count++;
            else
                count=1;
                
            local_max = max(local_max,count);
        }
        maxi = max(maxi,local_max);
	    for(int x=0;x<n;x++){
	        vector<int> temp = v;
	        int local_max=1,count=1;
	        temp[x]*=k;
	        for(int y=1;y<n;y++){
	            if(temp[y-1]<=temp[y])
	                count++;
	            else
	                count=1;
	                
	            local_max = max(local_max,count);
	        }
	        maxi = max(maxi,local_max);
	     }
	    cout<<maxi<<endl;
	}
}
