import java.util.*;
import java.io.*;

public class replication {
  private static int count = 0;
  private static String[][] farm;
  private static int D;
  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(new File("replication.in"));
    int N = in.nextInt();
    D = in.nextInt();
    ArrayList<Integer> startCoords = new ArrayList<Integer>();
    

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
    System.out.println(startCoords);
    for (int startPosition: startCoords) {
      int r = startPosition/1000;
      int c = startPosition%1000;
      recursion (r, c, 0, 1);
    }
  }
  public static void recursion(int r, int c, int size, int hour) {
    boolean allDie = false;
    ArrayList<Integer> newPositions = new ArrayList<Integer>();

    //checking borders
    int currR = r - size;
    int currC = c;
    while (currC <= c + size) {
      if (farm[currR][currC].equals("#")) {
        allDie = true; break;
      }
      else if (!farm[currR][currC].equals("X")) {
        newPositions.add(currR*1000 + currC);
      }
      currC++; currR++;
    }
    currC=-2;
    while (currC >= c) {
      if (farm[currR][currC].equals("#")) {
        allDie = true; break;
      }
      else if (!farm[currR][currC].equals("X")) {
        newPositions.add(currR*1000 + currC);
      }
      currC--; currR++;
    }
    currR=-2;
    while (currC >= c - size) {
      if (farm[currR][currC].equals("#")) {
        allDie = true; break;
      }
      else if (!farm[currR][currC].equals("X")) {
        newPositions.add(currR*1000 + currC);
      }
      currC--; currR--;
    }
    currC=+2;
    while (currC <= c) {
      if (farm[currR][currC].equals("#")) {
        allDie = true; break;
      }
      else if (!farm[currR][currC].equals("X")) {
        newPositions.add(currR*1000 + currC);
      }
      currC++; currR--;
    }

    if (allDie) {
      return;
    }
    count += newPositions.size();
    for (int each: newPositions) {
      int newR = each/1000; int newC = each%1000;
      farm[newR][newC] = "X";
    }
    //replicating
    if (D == 1 || hour % D == 0) {
      recursion(r, c, size + 1, hour + 1);
    }
    //moving in four directions
    else {
      recursion(r+1, c, size, hour);
      recursion(r-1, c, size, hour);
      recursion(r, c+1, size, hour);
      recursion(r, c-1, size, hour);
    }
  }
}
