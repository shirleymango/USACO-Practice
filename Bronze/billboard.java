/* USACO 2017 December Contest, Bronze
 * Problem 1. Blocked Billboard
 */

import java.util.*;
import java.io.*;

public class billboard {
	private static int xBottom1; private static int yBottom1; private static int xTop1; private static int yTop1;
	private static int xBottom2; private static int yBottom2; private static int xTop2; private static int yTop2;
	private static int xBottom3; private static int yBottom3; private static int xTop3; private static int yTop3;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File ("billboard.in"));
		xBottom1 = in.nextInt(); yBottom1 = in.nextInt(); xTop1 = in.nextInt(); yTop1 = in.nextInt();
		xBottom2 = in.nextInt(); yBottom2 = in.nextInt(); xTop2 = in.nextInt(); yTop2 = in.nextInt();
		xBottom3 = in.nextInt(); yBottom3 = in.nextInt(); xTop3 = in.nextInt(); yTop3 = in.nextInt();
		
		int count = 0;
		for (int i = xBottom1; i < xTop1; i++) {
			for (int j = yBottom1; j< yTop1; j++) {
				if (!overlapWithTruck(i, j)) {
					count++;
				}	
			}
		}
		
		for (int i = xBottom2; i < xTop2; i++) {
			for (int j = yBottom2; j< yTop2; j++) {
				if (!overlapWithTruck(i, j)) {
					count++;
				}	
			}
		}
		
		System.out.println(count);
		PrintWriter out = new PrintWriter(new File ("billboard.out"));
		out.print(count);
		out.close();
	}
	
	public static boolean overlapWithTruck (int x, int y) {
		if (x >= xBottom3 && y >= yBottom3 && x + 1 <= xTop3 && y + 1 <= yTop3) {
			return true;
		}
		return false;
	}

}
