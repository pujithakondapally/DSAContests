// 🌳 Problem: Perfect Square Ancestor Multiples in a Tree
// You are given a tree with n nodes, numbered from 0 to n - 1.
// Each node has an associated positive integer value.

// Your task is to compute, for every node i (where i ≠ 0), how many of its ancestors (nodes on the path from the root node 0 to node i, excluding i) satisfy the following condition:

// The product of the ancestor's value and the value of node i is a perfect square.

// Let vᵢ be this count for node i.
// Your goal is to output the sum of all vᵢ for nodes 1 through n - 1.

// 🧾 Input Format
// An integer n — the number of nodes in the tree.

// An array val of length n — where val[i] is the value assigned to node i.

// n - 1 lines each containing two integers u and v — representing an undirected edge between nodes u and v.

// 📤 Output Format
// Output a single integer — the sum of all valid vᵢ values for nodes 1 through n - 1.

// 🧪 Example 1
// Input:

// 3  
// 2 8 18  
// 0 1  
// 1 2
// Tree Structure:

//     0 (2)
//     |
//     1 (8)
//     |
//     2 (18)
// Evaluation:
// Node 1:

// Ancestor: Node 0

// 2 × 8 = 16 → ✅ perfect square → v₁ = 1

// Node 2:

// Ancestors: Nodes 0 and 1

// 18 × 8 = 144 → ✅

// 18 × 2 = 36 → ✅ → v₂ = 2

// Final Output:

// v₁ + v₂ = 1 + 2 = 3
// Output:
// Copy
// Edit
// 3
// 🧪 Example 2
// Input:
// 4  
// 3 6 5 2  
// 0 1  
// 0 2  
// 2 3
// Tree Structure:

//       0 (3)
//      / \
//   1(6) 2(5)
//            \
//             3(2)
// Evaluation:
// Node 1:

// Ancestor: Node 0 → 3 × 6 = 18 ❌ → v₁ = 0

// Node 2:

// Ancestor: Node 0 → 3 × 5 = 15 ❌ → v₂ = 0

// Node 3:

// Ancestors: Node 2 and Node 0

// 5 × 2 = 10 ❌

// 3 × 2 = 6 ❌ → v₃ = 0

// Final Output:
// 0 + 0 + 0 = 0
// Output:
// 0

import java.util.*;

public class SquareAncestors {
    static void dfs(int curr, int parent, int[] val, List<List<Integer>> tree,
                    List<Integer> ancestors, long[] ans) {
        int count = 0;
        for (int anc : ancestors) {
            long prod = 1L * val[curr] * val[anc];
            long sq = (long) Math.sqrt(prod);
            if (sq * sq == prod) {
                count++;
            }
        }

        ans[0] += count;

        ancestors.add(curr);
        for (int child : tree.get(curr)) {
            if (child != parent) {
                dfs(child, curr, val, tree, ancestors, ans);
            }
        }
        ancestors.remove(ancestors.size() - 1); // backtrack
    }

    static long solve(int n, int[] val, int[][] edge) {
        long result = 0;
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) tree.add(new ArrayList<>());

        for (int[] e : edge) {
            tree.get(e[0]).add(e[1]);
            tree.get(e[1]).add(e[0]);
        }

        long[] ans = new long[1]; // ✅ fix: previously was new long[0]
        dfs(0, -1, val, tree, new ArrayList<>(), ans);

        result = ans[0];
        return result;
    }

    // Sample main method to test the function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of nodes
        int[] val = new int[n];
        for (int i = 0; i < n; i++) val[i] = sc.nextInt();

        int[][] edge = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            edge[i][0] = sc.nextInt();
            edge[i][1] = sc.nextInt();
        }

        System.out.println(solve(n, val, edge));
    }
}
