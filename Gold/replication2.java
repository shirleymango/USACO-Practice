import java.util.*;
import java.io.*;

public class replication2 {
  private static int count = 0;
  private static String[][] farm;
  private static int D;
  private static ArrayList<Integer> visited;
  
  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    D = in.nextInt();
    ArrayList<Integer> startCoords = new ArrayList<Integer>();
    visited = new ArrayList<Integer>();

    String line = in.nextLine();
    farm = new String[N][N];
    for (int i = 0; i < N; i++) {
      line = in.nextLine();
      String[] arr = line.split("");
      farm[i] = arr;
      //find index of 'S' squares
      while (line.indexOf('S') >= 0) {
        int index = line.indexOf('S');
        startCoords.add(1000*i + index);
        line = line.substring(0, index) + "." + line.substring(index + 1);
      }
    }
    for (int startPosition: startCoords) {
      int r = startPosition/1000;
      int c = startPosition%1000;
      recursion (r, c, 0, 0);
    }
    System.out.println(count);
    for (String[] each: farm) {
    	System.out.println(Arrays.toString(each));
    }
    in.close();
  }
  public static void recursion(int r, int c, int size, int hour) {
    ArrayList<Integer> newPositions = new ArrayList<Integer>();
    //add to visited list
    visited.add(1000*r + c);
    
    //checking borders
    int currR = r - size;
    int currC = c;
    while (currC <= c + size) {
      if (farm[currR][currC].equals("#")) {
        return;
      }
      else if (!farm[currR][currC].equals("X")) {
        newPositions.add(currR*1000 + currC);
      }
      currC++; currR++;
    }
    currC-=2;
    while (currC >= c) {
      if (farm[currR][currC].equals("#")) {
        return;
      }
      else if (!farm[currR][currC].equals("X")) {
        newPositions.add(currR*1000 + currC);
      }
      currC--; currR++;
    }
    currR-=2;
    while (currC >= c - size) {
      if (farm[currR][currC].equals("#")) {
        return;
      }
      else if (!farm[currR][currC].equals("X")) {
        newPositions.add(currR*1000 + currC);
      }
      currC--; currR--;
    }
    currC+=2;
    while (currC < c) {
      if (farm[currR][currC].equals("#")) {
        return;
      }
      else if (!farm[currR][currC].equals("X")) {
        newPositions.add(currR*1000 + currC);
      }
      currC++; currR--;
    }

    count += newPositions.size();
    for (int each: newPositions) {
    	int newR = each/1000; int newC = each%1000;
    	farm[newR][newC] = "X";
    }
    //replicating
    if (hour != 0 && (D == 1 || hour == D)) {
    	recursion(r, c, size + 1, 0);
    }
    //moving in four directions
    else {
    	if (!visited.contains((r+1)*1000 + c)) {recursion(r+1, c, size, hour+1);}
    	if (!visited.contains((r-1)*1000 + c)) {recursion(r-1, c, size, hour+1);}
    	if (!visited.contains((r)*1000 + c+1)) {recursion(r, c+1, size, hour+1);}
    	if (!visited.contains((r)*1000 + c-1)) {recursion(r, c-1, size, hour+1);}
    }
  }
}
