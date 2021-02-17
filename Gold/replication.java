import java.util.*;

public class replication {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int D = in.nextInt();
		int count = 0;
		boolean flag = false;
		
		HashSet<Integer> visited = new HashSet<Integer>();
	    //create new stacks: coords, hour, size
	    Stack<Integer> coords = new Stack<Integer>();
	    Stack<Integer> hours = new Stack<Integer>();
	    Stack<Integer> sizes = new Stack<Integer>();
	    
	    String line = in.nextLine();
	    String[][] farm = new String[N][N];
	    
	    for (int i = 0; i < N; i++) {
	        line = in.nextLine();
	        String[] arr = line.split("");
	        farm[i] = arr;
	        //find index of 'S' squares
	        while (line.indexOf('S') >= 0) {
	          int index = line.indexOf('S');
	          coords.add(1000*i + index);
	          hours.add(0);
	          sizes.add(0);
	          line = line.substring(0, index) + "." + line.substring(index + 1);
	        }
	      }
	    //dfs
	    while (!coords.isEmpty()) {
	    	int currentPosition = coords.pop();
	    	int size = sizes.pop();
	    	int hour = hours.pop();
	    	
	    	int r = currentPosition/1000;
	        int c = currentPosition%1000;
	        ArrayList<Integer> newPositions = new ArrayList<Integer>();
	        //add to visited list
	        visited.add(currentPosition);
	        
	        //checking borders
	        int currR = r - size;
	        int currC = c;
	        while (currC <= c + size) {
	          if (farm[currR][currC].equals("#")) {
	            flag = true; break;
	          }
	          else if (!farm[currR][currC].equals("X")) {
	            newPositions.add(currR*1000 + currC);
	          }
	          currC++; currR++;
	        }
	      //break if the robot hit rock
	        if (flag) {
	        	flag = false;
	        	continue;
	        }
	        currC-=2;
	        
	        while (currC >= c) {
	          if (farm[currR][currC].equals("#")) {
	            flag = true; break;
	          }
	          else if (!farm[currR][currC].equals("X")) {
	            newPositions.add(currR*1000 + currC);
	          }
	          currC--; currR++;
	        }
	      //break if the robot hit rock
	        if (flag) {
	        	flag = false;
	        	continue;
	        }
	        currR-=2;
	        while (currC >= c - size) {
	          if (farm[currR][currC].equals("#")) {
	            flag = true; break;
	          }
	          else if (!farm[currR][currC].equals("X")) {
	            newPositions.add(currR*1000 + currC);
	          }
	          currC--; currR--;
	        }
	      //break if the robot hit rock
	        if (flag) {
	        	flag = false;
	        	continue;
	        }
	        currC+=2;
	        
	        while (currC < c) {
	          if (farm[currR][currC].equals("#")) {
	            flag = true; break;
	          }
	          else if (!farm[currR][currC].equals("X")) {
	            newPositions.add(currR*1000 + currC);
	          }
	          currC++; currR--;
	        }
	        //break if the robot hit rock
	        if (flag) {
	        	flag = false;
	        	continue;
	        }
	        //update count and change visited positions to "X"	
	        count += newPositions.size();
	        for (int each: newPositions) {
	        	int newR = each/1000; int newC = each%1000;
	        	farm[newR][newC] = "X";
	        }
	        //replicating
	        if (hour == D) {
	        	coords.add(currentPosition);
	        	sizes.add(size + 1);
	        	hours.add(0);
	        }
	        //moving
	        else {
	        	if (!visited.contains(currentPosition+1000) && !farm[r+1][c].equals("#")) {coords.add(currentPosition+1000); sizes.add(size); hours.add(hour+1);}
	        	if (!visited.contains(currentPosition-1000) && !farm[r-1][c].equals("#")) {coords.add(currentPosition-1000); sizes.add(size); hours.add(hour+1);}
	        	if (!visited.contains((r)*1000 + c+1) && !farm[r][c+1].equals("#")) {coords.add(currentPosition+1); sizes.add(size); hours.add(hour+1);}
	        	if (!visited.contains((r)*1000 + c-1) && !farm[r][c-1].equals("#")) {coords.add(currentPosition-1); sizes.add(size); hours.add(hour+1);}
	        }
	    }
	    
	    System.out.println(count);
		
	}

}
