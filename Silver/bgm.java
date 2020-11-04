/* USACO 2015 US Open, Silver
 * Problem 1. Bessie Goes Moo
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class bgm {

	public static void main(String[] args) throws IOException {
	/* how many ways can FJ choose the integer values of the variables to make 
	 * (B+E+S+S+I+E)(G+O+E+S)(M+O+O) be a multiple of 7?
	 */

		Scanner in = new Scanner(new File("bgm.in"));
		long[][] array = new long [26][7]; int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			char letter= in.next().charAt(0);
			int a = in.nextInt();
			int k = (a%7+7)%7;
			array[(int)letter-65][k]++;
		}
		long count = 0;
		for (int B = 0; B < 7; B++) {
			for (int E = 0; E < 7; E++) {
				for (int S = 0; S < 7; S++) {
					for (int I = 0; I < 7; I++) {
						for (int G = 0; G < 7; G++) {
							for (int O = 0; O < 7; O++) {
								for (int M= 0; M < 7; M++) {
									if ((B+E+S+S+I+E)*(G+O+E+S)*(M+O+O)%7==0) {
										long num = array[(int)'B'-65][B]*array[(int)'E'-65][E]*array[(int)'S'-65][S]*array[(int)'I'-65][I]*array[(int)'G'-65][G]*array[(int)'O'-65][O]*array[(int)'M'-65][M];
										count +=num;
									}
								}
							}
						}
					}
				}
			}	
		}
		System.out.println(count);
		PrintWriter out = new PrintWriter(new File("bgm.out"));
		out.print(count);
		out.close();
	}

}
