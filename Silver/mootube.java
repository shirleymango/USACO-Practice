/* USACO 2018 January Contest, Silver
 * Problem 3. MooTube
 */

import java.io.*;
import java.util.*;

public class mootube {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("mootube.in"));
		PrintWriter out = new PrintWriter(new File("mootube.out"));
		int N = in.nextInt(); int Q = in.nextInt();
		int[][] relevances = new int[N+1][N+1];
		
		for (int i = 0; i < N-1; i++) {
			int node1 = in.nextInt(); int node2 = in.nextInt(); int relevance = in.nextInt();
			relevances[node1][node2] = relevance;
			relevances[node2][node1] = relevance;
		}
		
		for (int i = 0; i < Q; i++) {
			int k = in.nextInt(); int v = in.nextInt(); int count = 0;
			LinkedList<Integer> list = new LinkedList<>();
			list.add(v); boolean[] visited = new boolean[N+1]; visited[v]=true;
			while (!list.isEmpty()) {
				int curr = list.poll();
				for (int j = 1; j <= N; j++) {
					if (relevances[curr][j] >= k && !visited[j]) {
						list.add(j); count++;
						visited[j] = true;
					}
				}
			}
			out.println(count);
		}
		out.close();
		in.close();
	}

}
