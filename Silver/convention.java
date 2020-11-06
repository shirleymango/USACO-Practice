/* USACO 2018 December Contest, Silver
 * Problem 1. Convention
 */

import java.io.*;
import java.util.*;

public class convention {
	private static int[] arr;
	private static int N;
	private static int M;
	private static int C;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner (new File("convention.in"));
		N = in.nextInt(); M = in.nextInt(); C = in.nextInt();
		
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		Arrays.sort(arr);
		System.out.println(binSearch(0, 1000000000));
		PrintWriter out = new PrintWriter(new File("convention.out"));
		out.print(binSearch(0, 1000000000));
		out.close();
	}
	
	public static boolean pos (int wait) {
		int wagons = 1;
		int firstArrival = arr[0];
		int firstIndex = 0;
		for(int i = 1; i < N; i++) {
			if (arr[i]-firstArrival> wait || i+1-firstIndex >C) {
				wagons++;
				firstArrival = arr[i];
				firstIndex = i;
			}
		}
		return wagons <= M;
	}
	
	public static int binSearch(int low, int high) {
		if(low == high) return low;
		if(low + 1 == high) {
			if(pos(low)) return low;
			return high;
		}
		int mid = (low + high)/2;
		if(pos(mid)) return binSearch(low, mid);
		else return binSearch(mid, high);
	}

}
