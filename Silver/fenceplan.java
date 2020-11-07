/* USACO 2019 US Open Contest, Silver
 * Problem 3. Fence Planning
 */

import java.io.*;
import java.util.*;

public class fenceplan {

	public static void main(String[] args) throws IOException {
		//floodfill
		Scanner in = new Scanner (new File("fenceplan.in"));
		int n = in.nextInt(); int m = in.nextInt();
		
		int[][] arr = new int[n+1][2];
		for (int c = 1; c< n+1; c++) {
			arr[c][0] = in.nextInt(); arr[c][1] = in.nextInt();
		}
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int j = 0; j < n+1 ; j++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int k = 0; k < m; k++) {
			int cow1 = in.nextInt(); int cow2 = in.nextInt();
			adj.get(cow1).add(cow2);
			adj.get(cow2).add(cow1);
		}
		
		boolean[] visited = new boolean[n+1]; int minPerimeter = Integer.MAX_VALUE;
		
		for (int i = 1; i < n+1; i++) {
			if (!visited[i]) {
				visited[i] = true;
				LinkedList<Integer> list = new LinkedList<Integer> (); 
				int xMin = Integer.MAX_VALUE; int xMax = Integer.MIN_VALUE;
				int yMin = Integer.MAX_VALUE; int yMax = Integer.MIN_VALUE;
				list.add(i); 
				while(!list.isEmpty()) {
					int curr = list.poll();
					if (arr[curr][0] < xMin) {xMin = arr[curr][0];} if (arr[curr][0] > xMax) {xMax = arr[curr][0];}
					if (arr[curr][1] < yMin) {yMin = arr[curr][1];} if (arr[curr][1] > yMax) {yMax = arr[curr][1];}
					for (int u = 0; u < adj.get(curr).size(); u++) {
						int num = adj.get(curr).get(u);
						if (!visited[num]) {
							visited[num] = true;
							list.add(num); 
						}
					}
				}
				int perimeter = 2*(xMax-xMin + yMax - yMin); 
				if (perimeter<minPerimeter) {
					minPerimeter = perimeter;
				}
			}
		}
		System.out.println(minPerimeter);
		PrintWriter out = new PrintWriter(new File("fenceplan.out"));
		out.print(minPerimeter);
		out.close();
	}

}
