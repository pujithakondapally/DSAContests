// Online C++ compiler to run C++ program online
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main() {
    int arr[] = {5,4,2,3,6};
    vector<int> v;
    int cost = 0;
    for(int i=0;i<5;i++){
        v.push_back(arr[i]);
    }
    priority_queue<int,vector<int>,greater<int>> pq;
    for(auto i:v){
        pq.push(i);
    }
    while(pq.size()>1){
        int a = pq.top();
        pq.pop();
        int b = pq.top();
        pq.pop();
        pq.push(a+b);
        cost+=(a+b);
    }
    cout<<cost;

    return 0;
}