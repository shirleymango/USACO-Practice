//USACO 2015 December Contest, Bronze 
//Problem 2. Speeding Ticket

import java.io.*;
import java.util.*;

public class speeding {

	public static void main(String[] args) throws IOException {
		//problem: given different intervals of speed limits and speeding times, find the max overspeed
		//IMPORTANT: THIS CODE IS LINEAR
		Scanner in = new Scanner(new File("speeding.in"));
		int n = in.nextInt(); int m = in.nextInt();
		int[][] limits = new int[n][2]; int[][] bessie = new int[m][2];
		limits[0][0] = in.nextInt(); limits[0][1] = in.nextInt();
		
		for (int x = 1; x < n; x++) {
			limits[x][0] = in.nextInt() + limits[x-1][0]; limits[x][1] = in.nextInt();
		}
		
		bessie[0][0] = in.nextInt(); bessie[0][1] = in.nextInt();
		for (int y = 1; y < m; y++) {
			bessie[y][0] = in.nextInt() + bessie[y-1][0]; bessie[y][1] = in.nextInt(); 
		}
		int bessieIndex = 0; int limitIndex = 0; int ans= 0;
		
		//linear search
		
		for (int i = 0; i < n+m; i++) {
			//if bessie and limit is equal, then make both larger afterwards
			if (bessie[bessieIndex][0] == limits[limitIndex][0]) {
				if (bessie[bessieIndex][1]-limits[limitIndex][1] > ans) {
					ans = bessie[bessieIndex][1]-limits[limitIndex][1];
				}
				if (bessieIndex + 1 < bessie.length) {bessieIndex++;} 
				if (limitIndex + 1 < limits.length) {limitIndex++;}
			}
			//if bessie is less than limit, then only make bessie larger afterwards
			else if (bessie[bessieIndex][0] < limits[limitIndex][0]) {
				if (bessie[bessieIndex][1]-limits[limitIndex][1] > ans) {
					ans = bessie[bessieIndex][1]-limits[limitIndex][1];
				}
				if (bessieIndex + 1 < bessie.length) {bessieIndex++;} 
			}
			//if limit is less than bessie, then only make limit larger afterwards
			else {
				if (bessie[bessieIndex][1]-limits[limitIndex][1] > ans) {
					ans = bessie[bessieIndex][1]-limits[limitIndex][1];
				}
				if (limitIndex + 1 < limits.length) {limitIndex++;}
			}
		}
		
		System.out.println(ans);
		PrintWriter out = new PrintWriter(new File("speeding.out"));
		out.print(ans);
		out.close();
	}

}
