// On the occasion of Yoga Day, the only yoga instructor in Chefland, Chef, has received numerous queries.
// Chef has managed to free up 
// 𝑁
// N hours from his busy schedule to conduct yoga sessions. There are two types of sessions that Chef offers:

// Type 1 1 session, which lasts 1 hour, and earns Chef x

// Type 2 session, which lasts 2 hours, and earns Chef 
// y
 
// 3
// 4 5 7
// 3 1 2
// 5 2 6

// 20
// 3
// 14

#include <bits/stdc++.h>
using namespace std;

int main() {
    int t;
    cin>>t;
    for(int i=0;i<t;i++){
        int n,first,second;
        cin>>n>>first>>second;
        int amount = 0;
        if(n>1 && 2*first<=second){
            
            amount = (n/2)*second + (n-(2*(n/2)))*first;   
        }
        else{
            amount += first*n;
        }
        cout<<amount<<endl;
    }
    

}
