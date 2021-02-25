import java.util.*;

public class replication {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long d = in.nextInt();
		int[] dr = new int[] {-1, 1, 0, 0};
		int[] dc = new int[] {0, 0, -1, 1};
		boolean[][] empty = new boolean[n][n];
		int[][] dist_rock = new int[n][n];
		int[][] dist_source = new int[n][n];
		Queue<Integer> starts = new LinkedList<Integer>();
		Queue<Integer> rocks = new LinkedList<Integer>();
		boolean[][] ans = new boolean[n][n];
		
		//Reading the input
		in.nextLine();
		for (int i = 0; i < n; i++) {
			String line = in.nextLine();
			for (int j = 0; j < n; j++) {
				//rocks
				if(line.charAt(j) =='#'){
					empty[i][j] = false;
					rocks.add(1000*i + j);
				}
				//not rocks
				else {
					empty[i][j] = true;
				}
				//starting points
				if (line.charAt(j) == 'S') {
					starts.add(1000*i + j);
				}
				//for all points
				dist_rock[i][j] = -1;
				dist_source[i][j] = -1;
				ans[i][j] = false;
			}
		}
		// First, we calculate distance of everything to a rock
		Queue<Integer> bfs = new LinkedList<Integer>();
		while (!rocks.isEmpty()) {
			int rock = rocks.remove();
			bfs.add(rock);
			dist_rock[rock/1000][rock%1000] = 0;
		}
		while(!bfs.isEmpty()) {
			int now = bfs.remove();
			for (int j = 0; j < 4; j++) {
				int toR = now/1000 + dr[j];
				int toC = now%1000 + dc[j];
				//check boundaries
				if (!(toR >= 0 && toR < n && toC >= 0 && toC < n)) {
					continue;
				}
				//check that the point is not already visited
				if (dist_rock[toR][toC] != -1) {
					continue;
				}
				dist_rock[toR][toC] = dist_rock[now/1000][now%1000] + 1;
				bfs.add(1000*toR + toC);
			}
		}
		//DEBUG: printing the dist_rock
//		System.out.println("Distance from nearest rock");
//		for (int j = 0; j < dist_rock.length; j++) {
//			System.out.println(Arrays.toString(dist_rock[j]));
//		}
		
		// Then, we do a BFS from the sources
		while (!starts.isEmpty()) {
			int start = starts.remove();
			bfs.add(start);
			dist_source[start/1000][start%1000] = 0;
		}
		// centers[i] will store all empty cells who are distance i+1 from a rock
		// (meaning they can replicate i times)
		ArrayList<Integer>[] centers = new ArrayList[n/2];
		for (int i = 0; i < centers.length; i++) {
			centers[i] = new ArrayList<Integer>();
		}
		while (!bfs.isEmpty()) {
			int now = bfs.remove();
			int nowR = now/1000; int nowC = now%1000;
			ans[nowR][nowC] = true;
			int now_dist = dist_source[nowR][nowC];
			centers[dist_rock[nowR][nowC]-1].add(now);
			//Do not continue if replicating would force robots to rocks
			if (now_dist >= d*dist_rock[nowR][nowC]) {
				continue;
			}
			for (int j = 0; j < 4; j++) {
				int toR = now/1000 + dr[j];
				int toC = now%1000 + dc[j];
				//check boundaries
				if (!(toR >= 0 && toR < n && toC >= 0 && toC < n)) {
					continue;
				}
				//check that the point is not already visited
				if (dist_source[toR][toC] != -1) {
					continue;
				}
				//check that the point is not a rock
				if (!empty[toR][toC]) {
					continue;
				}
				//Do not continue if moving would force robots to rocks
				if ((now_dist + 1) > d*dist_rock[toR][toC]) {
					continue;
				}
				dist_source[toR][toC] = now_dist + 1;
				bfs.add(1000*toR+toC);
			}
		}
//		DEBUG: PRINT OUT centers
//		System.out.println("Centers and maximum size of swarm at centers");
//		for(int j = 0; j < centers.length; j++) {
//			System.out.println(centers[j]);
//		}
		
		// Do a modified BFS such that we reach every cell where
		// there is some other cell in centers[z] and the distance
		// between the two is <=z
		Queue<Integer> next_stage = new LinkedList<Integer>();
		for (int i = n/2-1; i >=0; i--) {
			//swap bfs and next_stage
			while(!next_stage.isEmpty()) {
				bfs.add(next_stage.remove());
			}
			while(!bfs.isEmpty()) {
				int now = bfs.remove();
				for (int j = 0; j < 4; j++) {
					int toR = now/1000 + dr[j];
					int toC = now%1000 + dc[j];
					//check boundaries
					if (!(toR >= 0 && toR < n && toC >= 0 && toC < n)) {
						continue;
					}
					//check that the point is not already visited
					if (ans[toR][toC]) {
						continue;
					}
					//check that the point is not a rock
					if (!empty[toR][toC]) {
						continue;
					}
					ans[toR][toC] = true;
					next_stage.add(1000*toR+toC);
				}
			}
			for (int each: centers[i]) {
				next_stage.add(each);
			}
		}
		int total = 0;
		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(ans[i]));
			for (int j = 0; j < n; j++) {
				if (ans[i][j]) {
					total++;
				}
			}
		}
		System.out.println(total);
	}
}
