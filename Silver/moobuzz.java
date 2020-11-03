/* USACO 2019 December Contest, Silver
 * Problem 1. MooBuzz 
 */

import java.io.*;
import java.util.*;
public class moobuzz {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(new File("moobuzz.in"));
		int n = in.nextInt();
		
		int[] arr = new int[] {1, 2, 4, 7, 8, 11, 13, 14};
		
		int m = n/8; int k = n%8;
		int ans;
		if (k == 0) {
			ans = (m-1)*15 + 14;
		}
		else {
			ans = m*15+ arr[k-1];
		}
		
		PrintWriter out = new PrintWriter(new File("moobuzz.out"));
		out.print(ans);
		System.out.println(ans);
		out.close();
	}

}
