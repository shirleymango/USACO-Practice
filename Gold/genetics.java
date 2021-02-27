import java.util.*;

public class genetics {

	public static void main(String[] args) {
		final long mod = 1000000007;
		final char[] bases = new char[] {'A', 'G', 'C', 'T'};
		Scanner in = new Scanner(System.in);
		char[] s = in.next().toCharArray();
		long[][][][] dp = new long[s.length][4][4][4];
		
		//First letter of given genome
		for (int n = 0; n < 4; n++) {
			for (int l = 0; l < 4; l++) {
				if (s[0] == '?' || s[0] == bases[l]) {
					dp[0][n][l][l] = 1L;
				}
			}
		}
		//Dynamic Programming 
		for (int k = 1; k < s.length; k++) {
			for (int m2 = 0; m2 < 4; m2++) {
				if (s[k] == '?' || s[k] == bases[m2]) {
					for (int n = 0; n < 4; n++) {
						for (int l = 0; l < 4; l++) {
							for (int m = 0; m < 4; m++) {
								//Extending last substring to include m2
								if (m != m2) {
									dp[k][n][l][m2] += dp[k-1][n][l][m];
									dp[k][n][l][m2] %= mod;
								}
								//Adding new substring of m2
								if (n == m) {
									dp[k][l][m2][m2] += dp[k-1][n][l][m];
									dp[k][l][m2][m2] %= mod;
								}
							}
						}
					}
				}
			}
		}
		
		//Summing up total answer
		long count = 0; 
		for (int l = 0; l < 4; l++) {
			for (int m = 0; m < 4; m++) {
				//first letter of second to last substring must equal to last letter of last substring
				count += dp[s.length - 1][m][l][m];
			}
		}
		count %= mod;
		System.out.println(count);

	}

}
