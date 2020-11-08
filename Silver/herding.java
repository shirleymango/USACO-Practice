/* USACO 2019 February Contest, Silver
 * Problem 1. Sleepy Cow Herding
 */

import java.io.*;
import java.util.*;

public class herding {
	public static void main(String[] args) throws IOException {
		/* finding the minimum is like binary search
		 * if the minimum is one, then the minimum moves may be two moves
		 * if minimum is not one, then minimum moves will be the that minimum number
		 * looking at the last move
		 */ 
		
		Scanner in  = new Scanner (new File("herding.in"));
		
		int n = in.nextInt();
		int[] cowLocations = new int[n];
		for (int i = 0; i < n; i ++) {
			cowLocations[i] = in.nextInt();
		}
		
		Arrays.sort(cowLocations);
		int firstGap = cowLocations[1] - cowLocations[0]-1;
		int lastGap = cowLocations[n-1] - cowLocations[n-2]-1;
		
		//find the minimum, n-1, look at the first gap and the last gap, if any of them is one then, last gap is greater than or 
		//a[1] - a[0] and a[n-1] - a[n-2] then you take one, x - cow size	
		int maxdiff = Integer.MIN_VALUE;
		for (int i = 0; i < n; i ++) {
			int insertionPoint = Arrays.binarySearch(cowLocations, cowLocations[i]+n-1); int diff = 0;
			if (insertionPoint < 0) {
				diff = -1*insertionPoint - i -1;
			}
			else { 
				diff = insertionPoint - i + 1;
			}
			if (diff > maxdiff) {
				maxdiff = diff;
			}
		}
		
		int minDistance = n - maxdiff; int min = 0;
		
		if (minDistance >= 2) {
			min = minDistance;
		}
		else if (minDistance == 0) {
			min = 0;
		}
		else {
			//look at the first and the last gap
			if (firstGap == 1 || lastGap == 1) {
				min = 1;
			}
			else {
				min = 2;
			}
		}
		/* binary search for a[i] + n-1, insertion point, n - most crowded region
		 */
		int max = 0; int sum= 0; 
		for (int i = 1; i < n; i ++) {
			int diff = cowLocations[i] - cowLocations[i-1]-1;
			sum += diff;
		}
		if (firstGap < lastGap) {
			max = sum - firstGap;
		}
		else {
			max = sum - lastGap;
		}
		System.out.println(min);
		System.out.print(max);
		PrintWriter out = new PrintWriter(new File("herding.out"));
		out.println(min);
		out.print(max);
		out.close();
	}

}
