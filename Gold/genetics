import java.util.*;

public class genetics {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		int l = s.length();
		long count = 0;
		String[] letters  = new String[] {"A", "G", "T", "U"};
		String[] possibleGenomes = new String[(int) Math.pow(4, l)];
		for(int i = 0; i < possibleGenomes.length; i++) {
			possibleGenomes[i] = "";
		}
		
		//find all possible genomes
		for (int i = 1; i <= l; i++) {
			int p1 = (int) (Math.pow(4, i-1));
			int p2 = (int) (Math.pow(4, l-i));
			int index = 0;
			for (int j = 0; j < p1; j++) {
				for (int k = 0; k < 4; k++) {
					for (int n = 0; n < p2; n++) {
						possibleGenomes[index] += letters[k];
						index++;
					}
				}
			}
		}
		
		for (int m = 0; m < possibleGenomes.length; m++) {
			String possibleStart = possibleGenomes[m]; //possible start genome
			//split at consecutive points
			ArrayList<String> divisions = new ArrayList<String>();
			int startIndex = 0;
			for (int i = 0; i < l - 1; i++) {
				if (possibleStart.charAt(i) == possibleStart.charAt(i+1)) {
					//split
					divisions.add(possibleStart.substring(startIndex, i+1));
					startIndex = i+1;
				}
			}
			divisions.add(possibleStart.substring(startIndex));
			
			//reverse
			ArrayList<String> reversed = new ArrayList<String>();
			for (int i = 0; i < divisions.size(); i++) {
				String curr = divisions.get(i);
				String r = "";
				for (int j = 0; j < curr.length(); j++) {
					r = curr.charAt(j) + r;
				}
				reversed.add(r);
			}
			
			//concatenate new
			String end = "";
			for (String str: reversed) {
				end += str;
			}
			
			//check if valid
			boolean valid = true;
			for (int i = 0; i < l; i++) {
				if (s.charAt(i) != '?' && end.charAt(i) != s.charAt(i)) {
					valid = false;
					break;
				}
			}
			if (valid) {
				count++;
			}
			
			if (count > 1000000007) {
				count = count % 1000000007;
			}
		}
		
		System.out.println(count);
	}

}
