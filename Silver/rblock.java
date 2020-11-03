//USACO 2014 February Contest, Silver
//Problem 2. Roadblock

import java.io.*;
import java.util.*;

public class rblock {

	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(new File("rblock.in"));
		
		int N = in.nextInt(); int M = in.nextInt();
		
		//create an adjacency list
		int[][] weights = new int[N+1][N+1];
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < N+1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			int node1 = in.nextInt(); int node2 = in.nextInt();
			int weight = in.nextInt();
			weights[node1][node2] = weight;
			weights[node2][node1] = weight;
			adj.get(node1).add(node2);
			adj.get(node2).add(node1);
		}
		
		int bfs[] = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			bfs[i] = Integer.MAX_VALUE;
		}
		bfs[1] = 0;
		
		int[] parent = new int[N+1]; //the parent node in the path taken
		
		Queue <Integer> q = new LinkedList<Integer>();
		q.add(1);
		while (!q.isEmpty()) {
			int t = q.poll();
			for (int each: adj.get(t)) {
				int weightOfEdge = weights[t][each];
				if(bfs[t] + weightOfEdge < bfs[each]) {
					q.add(each);
					bfs[each] = bfs[t] + weightOfEdge;
					parent[each] = t;
				}
			}
		}
		
		//iterating through every pathway that was on Farmer J's original route
		int maxNew = 0;
		int currentNode = N; int parentNode = parent[N]; 
		while (currentNode != 1) {
			int newWeight = 2 * weights[currentNode][parentNode];
			weights[currentNode][parentNode] = newWeight;
			weights[parentNode][currentNode] = newWeight;
			int newLength = bfs(adj, weights);
			if (newLength > maxNew) {
				maxNew = newLength;
			}
			weights[currentNode][parentNode] = newWeight/2;
			weights[parentNode][currentNode] = newWeight/2;
			currentNode = parentNode; parentNode = parent[currentNode];
		}
		
		System.out.println(maxNew - bfs[N]);
		PrintWriter out = new PrintWriter(new File("rblock.out"));
		out.print(maxNew - bfs[N]);
		out.close();
	}
	
	public static int bfs(ArrayList<ArrayList<Integer>> adj, int[][] weights) {
		int N = weights.length-1;
		int bfs[] = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			bfs[i] = Integer.MAX_VALUE;
		}
		bfs[1] = 0;
		
		Queue <Integer> q = new LinkedList<Integer>();
		q.add(1);
		while (!q.isEmpty()) {
			int t = q.poll();
			for (int each: adj.get(t)) {
				int weightOfEdge = weights[t][each];
				if(bfs[t] + weightOfEdge < bfs[each]) {
					q.add(each);
					bfs[each] = bfs[t] + weightOfEdge;
				}
			}
		}
		return bfs[N];
	}

}
