#include <bits/stdc++.h>
using namespace std;


int drow[] = {-1,1,0,0};
int dcol[] = {0,0,-1,1};

int desertQueen(vector<vector<char>> &grid,int n){
    vector<vector<int>> minWater(n,vector<int>(n,INT_MAX));
    queue<pair<int,int>> q;
    
    q.push({0,0});
    int x,y;
    minWater[0][0] = 0;
    while(!q.empty()){
        x = q.front().first;
        y = q.front().second;
        q.pop();
        for(int k=0;k<4;k++){
            int r = x + drow[k];
            int c = y + dcol[k];
            if(r>=0 && r<n && c>=0 && c<n){
                int cost = (grid[x][y]=='T' && grid[r][c]=='T')?0:1;
                if(minWater[x][y]+cost<minWater[r][c]){
                    minWater[r][c] = minWater[x][y] + cost;
                    q.push({r,c});
                }
            }
        }
    }
    return minWater[n-1][n-1];
}

int main(){
  int n;
  cin>>n;
  vector<vector<char>> grid(n,vector<char>(n,'.'));
  for(int i=0;i<n;i++){
    for(int j=0;j<n;j++){
      cin>>grid[i][j];
    }
  }
  int ans = 0;
  cout<<desertQueen(grid,n);
}