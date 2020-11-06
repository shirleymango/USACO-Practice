/* USACO 2014 December Contest, Silver
 * Problem 3. Cow Jog
 */

import java.util.*;
import java.io.*;

public class cowjog {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("cowjog.in"));
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
		int count = 0;
		
		int n = in.nextInt(); int t = in.nextInt();
		for (int i = 0; i < n; i++) {
			int spot = in.nextInt(); int speed = in.nextInt();
			arr.add(new ArrayList<Integer>(Arrays.asList(spot, speed)));
		}
		
		for (int i = n-1; i >= 0; i--) {
			int firstFinalSpot = arr.get(i).get(0) + arr.get(i).get(1)*t;
			while (i > 0 && (arr.get(i-1).get(0) + arr.get(i-1).get(1)*t) >= firstFinalSpot) {
				i--;
			}
			count++;
		}
		System.out.println(count);
		
		PrintWriter out = new PrintWriter(new File("cowjog.out"));
		out.print(count);
		out.close();
	}
	//solution on website: count the number of cows that don't cross the cow in front of them
	//start from the back and keep track of the least far ending position as you go

}
