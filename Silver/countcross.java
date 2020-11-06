/* USACO 2017 February Contest, Silver 
 * Problem 3. Why Did the Cow Cross the Road III
 */

import java.io.*;
import java.util.*;
public class countcross {	
	public static void main(String[] args) throws IOException {		
		//FLOODFILL
		Scanner in = new Scanner(new File("countcross.in"));
		int N = in.nextInt(); int K = in.nextInt(); int R = in.nextInt();
		
		ArrayList<String> roads = new ArrayList<String>();
		for (int i = 0; i < R; i++) {
			String s1 = in.nextInt() + " " + in.nextInt();
			String s2 = in.nextInt() + " " + in.nextInt();
			roads.add(s1); roads.add(s2);
		}
		int[][] cowsInFields = new int[N+1][N+1];
		for (int i = 0; i < K; i++) {
			int r = in.nextInt(); int c = in.nextInt();
			cowsInFields[r][c]++;
		}
		boolean[][] isFilled = new boolean[N+1][N+1];
		int output = 0;
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if (!isFilled[i][j]) {
					LinkedList<String> list = new LinkedList<String> (); //creating the linked list for the floodfill
					list.add(i + " " + j); isFilled[i][j] = true;
					int numOfCows = 0;
					while(!list.isEmpty() ) {
						String curr = list.poll();
						String indexes[] = curr.split(" ");
						int r = Integer.parseInt(indexes[0]); //index for the row
						int c = Integer.parseInt(indexes[1]); //index for the column
						numOfCows += cowsInFields[r][c]; //adding the number of cows in the field into the total number of cows in the section
						
						ArrayList <String> blocked = new ArrayList<String>();
						
						//find which roads are blocked:
						while (roads.contains(curr)) {
							int index = roads.indexOf(curr);
							if (index%2 == 0) {
								blocked.add(roads.get(index + 1));
							}
							else {
								blocked.add(roads.get(index - 1));
							}
							roads.set(index, "");
						}
						
						int rUp = r+1; int rDown = r-1; int cLeft = c-1; int cRight = c+1;
						String up = rUp + " " + c; String down = rDown + " " + c; String left = r + " " + cLeft; String right = r + " " + cRight;
						
						//the adjacent field should be added to the list if it has not been filled already and is not blocked:
						if (r>1 && !blocked.contains(down) && !isFilled[rDown][c]) {
							list.add(down); isFilled[rDown][c] = true;
						}
						if (r<N && !blocked.contains(up) && !isFilled[rUp][c]) {
							list.add(up); isFilled[rUp][c] = true;
						}
						if (c>1 && !blocked.contains(left) && !isFilled[r][cLeft]) {
							list.add(left); isFilled[r][cLeft] = true;
						}
						if (c < N&& !blocked.contains(right) && !isFilled[r][cRight]) {
							list.add(right); isFilled[r][cRight] = true;
						}
					}
					output += (numOfCows)*(K-numOfCows);
				}
			}
		}
		output/=2;
		
		PrintWriter out = new PrintWriter(new File("countcross.out"));
		System.out.println(output);
		out.print(output);
		out.close();
	}

}
