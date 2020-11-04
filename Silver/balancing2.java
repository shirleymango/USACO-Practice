//2nd version of solution to USACO 2016 February Contest, Silver Problem 2. Load Balancing

/* 
 * This version keeps an ArrayList<ArrayList<Integer>> LL, for each x-value, 
 * a list of y coordinates are saved. First for each y=b (b takes one 
 * y-coordinate + 1), run a loop on each element (a list itself) of LL: 
 * For each x=a (a takes one x-coordinate + 1), increase m3 by the 
 * number of cows at a-1 but y-coordinate less than b; increase m2 by the
 * number of cows at a-1 but y-coordinate greater than b.
 * 
 * This version has complexity less than n*n, but larger than n * log n.
 */

import java.io.*;
import java.util.*;

public class balancing2 {

	public static void main(String[] args) throws IOException {

		// read input
		BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] pts = new int[N][2];
		
		int[] Y = new int[N];  // only the y-coordinates
		
 		for(int k=0; k<N; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pts[k][0] = Integer.parseInt(st.nextToken());
			pts[k][1] = Integer.parseInt(st.nextToken());
			Y[k] = pts[k][1];
		}
		br.close();

		// sort the points by x-coordinates and then y-coordinates
		Arrays.sort(pts, (a, b)-> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0] );

		Arrays.sort(Y);  // sort only the Y-coordinates
		
		// build an arrayList of x-list
		ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
	
		int currX = 0, currListID = -1;
		for(int k=0; k<N; k++) {
			if( pts[k][0]>currX ) {
				lists.add(new ArrayList<Integer>() );
				currX = pts[k][0];
				currListID++;
			}
			lists.get( currListID ).add(pts[k][1]);
		}
		
		// loop x=a and y=b and find the minimum of M
		
		int min = Integer.MAX_VALUE;
		for(int i=0; i<Y.length-1; i++) {

			if( Y[i+1] == Y[i] ) continue;

			int b = Y[i]+1;

			// compute m12 and m34
			int m12 = 0;
			for(int k=0; k<N; k++) {
				if( pts[k][1]>b )
					m12++;
			}
			int m34 = N - m12;

			// compute m1, m2, m3, m4
			
			int m1, m2=0, m3=0, m4;
			m1 = m12;
			m4 = m34;
			int count = Math.max( Math.max(m1, m2), Math.max(m3, m4) );				
			min = Math.min(count, min);
			
			for(int j=0; j<lists.size(); j++) {

				int loc = -Collections.binarySearch(lists.get(j), b) - 1;
				m3 += loc;
				m2 += lists.get(j).size() - loc;

				m1 = m12 - m2;
				m4 = m34 - m3;
				
				count = Math.max( Math.max(m1, m2), Math.max(m3, m4) );				
				min = Math.min(min, count);
			}			
		}
		System.out.println(min);
		PrintWriter output = new PrintWriter(new File("balancing.out"));
		output.println(min);
		output.close();
	}
}
