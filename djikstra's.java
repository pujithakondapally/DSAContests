/*Example 1:
input=5 
0 6 5 0 13
6 0 12 9 5
5 12 0 0 0
0 9 0 0 7
13 5 0 7 0
2
output=
Vertex           Distance from Source
0                5
1                11
2                0
3                20
4                16

Example 2:
input =9 
0 4 0 0 0 0 0 8 0
4 0 8 0 0 0 0 11 0
0 8 0 7 0 4 0 0 2
0 0 7 0 9 14 0 0 0
0 0 0 9 0 10 0 0 0
0 0 4 14 10 0 2 0 0
0 0 0 0 0 2 0 1 6
8 11 0 0 0 0 1 0 7
0 0 2 0 0 0 6 7 0
5
Vertex           Distance from Source
0                11
1                12
2                4
3                11
4                10
5                0
6                2
7                3
8                6
*/


// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph
import java.util.*;
import java.io.*;

class ShortestPath {
	// A utility function to find the vertex with minimum distance value,
	// from the set of vertices not yet included in shortest path tree
	static int V;
	int minDistance(int dist[], Boolean spt[]){
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v < V; v++){
			//System.out.println("v " + v + " spt[v] " + spt[v] + " dist[v] " + dist[v] + " min " + min);
			if (spt[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
				System.out.println("min " + min + " min_index " + min_index);
			}
		}
		printSolution(dist, -1);
		return min_index;
	}

	// Print the constructed distance array
	void printSolution(int dist[], int src){
		System.out.println("Vertex \t Distance from Source " + src);
		for (int i = 0; i < V; i++)
			System.out.println(i + " \t " + dist[i]);
	}

	// Function that implements Dijkstra's single source shortest path
	// algorithm for a graph represented using adjacency matrix
	void dijkstra(int graph[][], int src){
		int dist[] = new int[V]; // The output array 
		// the shortest distance from src to i
		// spt[i] will be true if vertex i is included in shortest
		// path tree or shortest distance from src to i is finalized
		Boolean spt[] = new Boolean[V];
		// Initialize all distances as INFINITE and spt[] as false
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			spt[i] = false;
		}
		// Distance of source vertex from itself is always 0
		dist[src] = 0;
		// Find shortest path for all vertices
		for (int count = 0; count < V - 1; count++) {
			// Pick the minimum distance vertex from the set of vertices not yet processed. 
			// u is always equal to src in first iteration
			int u = minDistance(dist, spt);
			// Mark the picked vertex as processed (shortest path tree)
			spt[u] = true;
			// Update dist value of the adjacent vertices of the picked vertex
			for (int v = 0; v < V; v++){
				// Update dist[v] only if is not in spt, there is an
				// edge from u to v, and total weight of path from src to
				// v through u is smaller than current value of dist[v]
				if (!spt[v] && graph[u][v] != 0 && 
					dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]){
						System.out.println("u " + u + " v " + v + " dist[u] " + dist[u] + " graph[u][v] " + graph[u][v] + " dist[v] " + dist[v]);
						dist[v] = dist[u] + graph[u][v];
						System.out.println("u " + u + " v " + v + " dist[v] " + dist[v]);
				}
			}
		}
		// print the constructed distance array
		printSolution(dist, src);
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		V = r;
		int[][] graph = new int[r][r];
		for(int i=0;i<r;i++){
			for(int j=0;j<r;j++)
				graph[i][j]=sc.nextInt();
		}
		ShortestPath t = new ShortestPath();
		int srcVertex = sc.nextInt();
		t.dijkstra(graph, srcVertex);
	}
}
