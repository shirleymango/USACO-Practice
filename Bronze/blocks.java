/* USACO 2016 December Contest, Bronze
 * Problem 2. Block Game
 */

import java.io.*;
import java.util.*;
public class blocks {

	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner (new File ("blocks.in"));
		int N = in.nextInt();
		int[] alphabet = new int[26]; //keep track of the output values
		String space = in.nextLine();
		
 		for (int i = 0; i < N; i++) {
			String s = in.nextLine(); //each line will have 2 words
			int[] firstword = new int[26]; 
			int j = 0;
			//loop through each character of the first word
			for (; j < s.length(); j++) {
				int index = (int) s.charAt(j);
				//break out of this loop when you get to space
				if (index == 32) {
					j++;
					break;
				}
				index -= 97;
				firstword[index]++;
				alphabet[index]+=1;
			}
			//loop through each character of the second word
			int[] secword = new int[26]; 
			while (j < s.length()) {
				int index = (int) s.charAt(j);
				index -= 97;
				secword[index]= secword[index]+ 1;
				if (secword[index]> firstword[index]) {
					alphabet[index]+=1;
				}
				
				j++;
			}
			
		}
 		
 		PrintWriter out = new PrintWriter(new File("blocks.out"));
 		for (int k= 0; k < 26; k++) {
 			System.out.println(alphabet[k]);
 			out.println(alphabet[k]);
 		}
 		out.close();
 		
	}

}
