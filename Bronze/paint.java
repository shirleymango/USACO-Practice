/* USACO 2015 December Contest, Bronze
 * Problem 1. Fence Painting
 */

import java.io.*;
import java.util.*;

public class paint {

	public static void main(String[] args) throws IOException {
		Scanner in  = new Scanner(new File("paint.in"));
		int[][] arr = new int[2][2];
		for (int x =0; x <2; x++) {
			arr[x][0] = in.nextInt();
			arr[x][1] = in.nextInt();
		}
		Arrays.sort(arr, (a, b) -> a[0]- b[0]);
		int ans = 0;
		//Case 1: no overlap
		if (arr[1][0] > arr[0][1]) {
			ans = arr[1][1] - arr[1][0] + arr[0][1] - arr[0][0];
		}
		else {
			//Case 2: some overlap
			if(arr[1][1] > arr[0][1]) {
				ans = arr[1][1] - arr[0][0];
			}
			//Case 3: complete overlap
			else {
				ans = arr[0][1] - arr[0][0];
			}
		}
		
		System.out.println(ans);
		PrintWriter out = new PrintWriter("paint.out");
		out.print(ans);
		out.close();
	}

}
