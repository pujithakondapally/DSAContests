#include <bits/stdc++.h>
using namespace std;

// Tree Node Structure
class Node {
public:
    int data;
    Node* left;
    Node* right;
    Node(int data) : data(data), left(nullptr), right(nullptr) {}
};

class Solution {
public:
    void buildGraph(Node* root, unordered_map<int, vector<int>>& graph) {
        if (!root) return;

        if (root->left) {
            graph[root->data].push_back(root->left->data);
            graph[root->left->data].push_back(root->data);
            buildGraph(root->left, graph);
        }

        if (root->right) {
            graph[root->data].push_back(root->right->data);
            graph[root->right->data].push_back(root->data);
            buildGraph(root->right, graph);
        }
    }

    int networkInfectionTime(Node* root, int start) {
        unordered_map<int, vector<int>> graph;
        buildGraph(root, graph);

        queue<int> q;
        unordered_set<int> visited;
        q.push(start);
        visited.insert(start);
        int time = 0;

        while (!q.empty()) {
            int size = q.size();
            bool spread = false;

            for (int i = 0; i < size; i++) {
                int node = q.front();
                q.pop();

                for (int neighbor : graph[node]) {
                    if (visited.find(neighbor) == visited.end()) {
                        visited.insert(neighbor);
                        q.push(neighbor);
                        spread = true;
                    }
                }
            }

            if (spread) time++;
        }

        return time;
    }
};

int main() {
    Node* root = new Node(3);
    root->left = new Node(1);
    root->right = new Node(4);
    root->left->left = new Node(5);
    root->left->right = new Node(2);
    root->right->left = new Node(6);
    root->right->right = new Node(7);

    Solution sol;
    int start = 1; 
    cout << "Minimum time to infect the entire network: " << sol.networkInfectionTime(root, start) << endl;

    return 0;
}
