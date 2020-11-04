//USACO 2016 February Contest
//Silver Problem 2. Load Balancing

import java.io.*;
import java.util.*;
public class balancing {
	/* create a grid where there is the least difference between the each quadrant
	 * in other words, try to find the least maximum among the different number of cows in each quadrant
	 */
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner (new File("balancing.in"));
		int n = in.nextInt();
		
		//brute force, just looking at the intervals, O(n^3)
		/* improvement: notice that the same info is used in different iterations of the loops 
		 * know the value of sum in I + II and III + IV
		 * use binary search to see where b lands in a column
		 * then always add the num to II and subtract the num from I
		 */
		int[][] xy = new int [n][2]; int[] yCoordinates = new int [n];
		for (int i = 0; i < n; i++) {
			int x = in.nextInt(); int y = in.nextInt();
			xy[i][0] = x; xy[i][1] = y;
			yCoordinates[i] = y;
		}
		
		ArrayList<Integer> maxValues = new ArrayList<Integer>();
		Arrays.sort(xy, (a, b)-> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0] ); Arrays.sort(yCoordinates);
		
		//have an arrayList of arrayLists that keeps track of the number of cows in each column
		ArrayList<ArrayList<Integer>> cowsInARow = new ArrayList<ArrayList<Integer>>();
		int currX = 0, currListID = -1;
		for (int k = 0; k < n; k++) {
			if (xy[k][0] > currX) {
				cowsInARow.add(new ArrayList<Integer>());
				currX = xy[k][0];
				currListID++;
			}
			cowsInARow.get(currListID).add(xy[k][1]);
		}
		
		//loop x = a and y = b so to find the min of maxes
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < yCoordinates.length-1; i++) {
			//making sure to skip if it is a duplicate
			
			if (yCoordinates[i] == yCoordinates[i+1]) {
				continue;
			}
			
			int b = yCoordinates[i] + 1;
			
			//compute m12 and m34
			int m12 = 0; int m34 = 0;
			
			for (int h = 0; h < n; h++) {
				if (xy[h][1]>b) 
				{m12++;}
			}
			m34 = n - m12; int max = 0;
			
			//compute m1, m2, m3, m4
			int m2 = 0; int m3 = 0;
			int m1 = m12; int m4 = m34;
			for (int k = 0; k < cowsInARow.size(); k++) {
				int loc = -(Collections.binarySearch(cowsInARow.get(k), b)) - 1;
				m3 += loc;
				m2 += cowsInARow.get(k).size() - loc;
				m1 = m12-m2;
				m4 = m34-m3;
			
				max = Math.max(Math.max(m1, m2), Math.max(m3, m4));
				min = Math.min(max, min);
			}	
		}
		
		
		System.out.println(min);
		PrintWriter out = new PrintWriter("balancing.out");
		out.print(min);
		out.close();
		
	}

}
