/* USACO 2015 December Contest, Gold
 * Problem 1. High Card Low Card
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class cardgame {

	public static void main(String[] args) throws IOException {
		
		/* Plan: order bessie's cards
		 * take her n/2 biggest cards to play for first half of game
		 * take her n/2 smallest cards to play for the second half of the game
		 * use the high card optimization for the first game, and reverse it for the second game
		 */
		
		BufferedReader br = new BufferedReader(new FileReader("cardgame.in")); 
		int n = Integer.parseInt(br.readLine());
		boolean[] elsieOwns = new boolean[2*n+1]; int points = 0;
		ArrayList<Integer> elsie1 = new ArrayList<Integer>(n);
		ArrayList<Integer> elsie2 = new ArrayList<Integer>(n);
		ArrayList<Integer> bessie = new ArrayList<Integer>(n);
		
		for (int i = 0; i < n/2; i++) {
			int index = Integer.parseInt(br.readLine());
			elsieOwns[index] = true;
			elsie1.add(index);
		}
		
		for (int i = n/2; i < n; i++) {
			int index = Integer.parseInt(br.readLine());
			elsieOwns[index] = true;
			elsie2.add(index);
		}
		
		for (int k = 1; k < 2*n+1; k++) {
			if (!elsieOwns[k]) {
				bessie.add(k);
			}
		}
		
		Collections.sort(elsie1); Collections.sort(elsie2);
		
		//high card wins
		boolean bessieWins = false; int bStart = n/2;
		for (int e = 0; e < n/2; e++) {
			int elsieCard = elsie1.get(e);
			for (int b = bStart; b < n; b++) {
				int bessieCard = bessie.get(b);
				if (bessieCard > elsieCard) {
					points++; bessieWins = true; bStart = b+1;
					break;
				}
			}
			if (!bessieWins) {
				break;
			}
		}
		
		//low card wins
		bessieWins = false; bStart = n/2 - 1;
		for (int e = n/2-1; e >= 0; e--) {
			int elsieCard = elsie2.get(e);
			for (int b = bStart; b >= 0; b--) {
				int bessieCard = bessie.get(b);
				if (bessieCard < elsieCard) {
					points++; bessieWins = true; bStart = b-1;
					break;
				}
			}
			if (!bessieWins) {
				break;
			}
		}
		System.out.println(points);
		
		PrintWriter out = new PrintWriter(new File("cardgame.out"));
		out.print(points);
		out.close();
	}

}
