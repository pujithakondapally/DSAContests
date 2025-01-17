#include <bits/stdc++.h>
using namespace std;

int calculateScore(string s,int n){
    stack<int> st;
    int score = 0;
    for(int i=0;i<2*n;i++){
        if(s[i]=='('){
            st.push(score);
            score = 0;
        }
        else{
            score = st.top() + max(2*score,1);
        }
    }
    return score;
}

void generateParanthesis(int open,int close,int &ans,string s,int n){
    if(open==0 && close==0){
        ans += calculateScore(s,n);
        return;
    }
    if(open<close){
        generateParanthesis(open,close-1,ans,s+")",n);
    }
    if(open>0){
        generateParanthesis(open-1,close,ans,s+"(",n);
    }
}

int main(){
    int n;
    cin>>n;
    int ans = 0;
    string s = "";
    generateParanthesis(n,n,ans,s,n);
    cout<<ans;
}

//Overall complexity: 𝑂(2^𝑁⋅𝑁)
// 2 -> 4
// 3 -> 17
