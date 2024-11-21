#include <bits/stdc++.h>
using namespace std;

int main() {
	int t;
	cin>>t;
	for(int i=0;i<t;i++){
	    int n;
	    cin>>n;
	    int p=1;
	    while(p*2<=n){
            p*=2;
        }
        int l = n-p;
        cout<<l*2<<endl;
	}

}

// Input
// Output
// 3
// 2 - 0
// 14 - 12
// 100000 - 68928

