// Problem Statement:

// You are given a grid of size M × N, where each cell contains one of the four directions: 'L' (left), 'R' (right), 'U' (up), 'D' (down). One of the cells contains a special character '*', which represents the destination. Starting from the top-left cell (position (0, 0)), your goal is to reach the cell marked with '*' in at most K seconds. Each move (in any direction) takes 1 second.

// However, you're only allowed to follow the directions given in the cells. If you want to move in a different direction than what's specified in a cell, you must change the direction in that cell, and each change counts as 1 modification.

// Your task is to determine the minimum number of direction changes required so that you can reach the destination in K seconds or less.
// If it is not possible to reach the '*' cell within K seconds, output -1.

// Example 1:
// Input:
// M = 2, N = 2, K = 2  
// Grid:
// R D  
// D *  

// Output: 0  
// Explanation: You can follow the path R → D → * without any changes in exactly 2 seconds.

// Example 2:
// Input:
// M = 2, N = 2, K = 0  
// Grid:
// R R  
// *  

// Output: -1  
// Explanation: You cannot reach the destination in 0 seconds.


import java.util.*;

/**
 *  Minimum arrow edits to reach '*' in ≤ K moves.
 *  Input format:
 *      m n k          // rows, columns, time limit
 *      m lines of n characters (L R U D or *) 
 *  Output:
 *      minimum edits, or -1 if impossible
 */
class MinMoves {
    private static final int INF = 1_000_000_000;
    private static final int[] DR = {-1, 0, 1, 0};   // U R D L
    private static final int[] DC = { 0, 1, 0,-1};
    private static final char[] DIR = {'U','R','D','L'};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt(), n = sc.nextInt(), K = sc.nextInt();
        sc.nextLine();                           // consume end‑line
        char[][] g = new char[m][n];
        int starR = -1, starC = -1;
        for (int i = 0; i < m; i++) {
            String row = sc.nextLine().trim();
            g[i] = row.toCharArray();
            int j = row.indexOf('*');
            if (j != -1) { starR = i; starC = j; }
        }

        int ans = dijkstra(g, m, n, K, starR, starC);
        System.out.println(ans);
    }

    private static int dijkstra(char[][] g, int m, int n, int K,
                                int targetR, int targetC) {

        // best[steps][r][c] = least edits seen so far
        int[][][] best = new int[K + 1][m][n];
        for (int s = 0; s <= K; s++)
            for (int i = 0; i < m; i++)
                Arrays.fill(best[s][i], INF);

        PriorityQueue<State> pq = new PriorityQueue<>();
        best[0][0][0] = 0;                       // start at (0,0), 0 changes
        pq.add(new State(0, 0, 0, 0));           // (changes, steps, r, c)

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.changes != best[cur.steps][cur.r][cur.c]) continue;

            // reached the star within allowed time -> answer found
            if (cur.r == targetR && cur.c == targetC) return cur.changes;

            if (cur.steps == K) continue;        // no time left to move

            // try moving in each of the four directions
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + DR[d], nc = cur.c + DC[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                int extra = (g[cur.r][cur.c] == DIR[d]) ? 0 : 1;
                int ns = cur.steps + 1, ncg = cur.changes + extra;

                if (ncg < best[ns][nr][nc]) {
                    best[ns][nr][nc] = ncg;
                    pq.add(new State(ncg, ns, nr, nc));
                }
            }
        }
        return -1;                               // unreachable within K moves
    }

    // Helper record for priority queue (min‑heap on 'changes')
    private record State(int changes, int steps, int r, int c)
            implements Comparable<State> {
        @Override public int compareTo(State o) {
            return Integer.compare(this.changes, o.changes);
        }
    }
}
