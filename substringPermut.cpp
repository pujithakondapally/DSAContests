//Return ans array where ans[i] denotes minimum characters needed from any prefix of s to construct any possible permuatations of the string arr[i].If is not possible to do so then ans[i] should be -1

// Input
// 10
// 7
// 1542097372
// 60165 75412 00146 07177 77335 99818 26603

// Output
// -1 7 -1 -1 -1 -1 -1


//{ Driver Code Starts
#include<bits/stdc++.h>
using namespace std;


class Array
{
public:
    template <class T>
    static void input(vector<T> &A,int n)
    {
        for (int i = 0; i < n; i++)
        {
            scanf("%d ",&A[i]);
        }
    }

    template <class T>
    static void print(vector<T> &A)
    {
        for (int i = 0; i < A.size(); i++)
        {
            cout << A[i] << " ";
        }
        cout << endl;
    }
};


// } Driver Code Ends

class Solution {
  public:
    vector<int> substringsAndPermutations(int n, int m, string s, vector<string>&arr) {
        map<char,vector<int>> c;
        for(int i=0;i<s.length();i++){
            c[s[i]].push_back(i);
        }
        vector<int> res;
        for(string &st:arr){
            int ans = -1;
            sort(st.begin(),st.end());
            int start = 0;
            while(start<st.size()){
                char ele = st[start];
                int end = start;
                while(end<st.size() && st[end]==ele){
                    end++;
                }
                if(c.find(ele)==c.end()){
                    ans = -1;
                    break;
                }
                if(c[ele].size()>=end-start){
                    ans = max(ans,c[ele][end-start-1]+1);
                }
                else{
                    ans = -1;
                    break;
                }
                start=end;
            }
            res.push_back(ans);
        }
        return res;
    }
};


//{ Driver Code Starts.

int main(){
    int t;
    scanf("%d ",&t);
    while(t--){
        
        int n;
        scanf("%d",&n);
        
        
        int m;
        scanf("%d",&m);
        
        
        string s;
        cin >> s;
        
        vector<string>arr;
        for (int i=0;i<m;i++){
            string a;
            cin >> a;
            arr.push_back(a);
        }
        
        Solution obj;
        vector<int> res = obj.substringsAndPermutations(n, m, s, arr);
        
        Array::print(res);
        
    
cout << "~" << "\n";
}
}

// } Driver Code Ends