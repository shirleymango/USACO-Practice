/* USACO 2016 December Contest, Bronze
 * Problem 3. The Cow-Signal
 */

import java.io.*;
import java.util.*;
public class cowsignal {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner (new File ("cowsignal.in"));
		int m = in.nextInt();
		int n = in.nextInt();
		int k = in.nextInt();
		String output = "";
		String empty = in.nextLine();
		for(int i = 0; i < m; i++) {
			String line = in.nextLine();
			String enlargedLine = "";
			for (int j = 0; j < n; j++) {
				char c = line.charAt(j);
				for (int l = 0; l < k; l++) {
					enlargedLine += "" + c;
				}
			}
			enlargedLine += "\n";
			for (int p = 0; p < k; p++) {
				output += enlargedLine;
			}
		}
		System.out.println(output);
		PrintWriter out = new PrintWriter(new File ("cowsignal.out"));
		out.print(output);
		out.close();
		in.close();
	}

}
