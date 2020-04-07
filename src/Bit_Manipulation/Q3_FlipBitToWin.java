package Bit_Manipulation;

import java.util.ArrayList;

public class Q3_FlipBitToWin {
	
	// VERSION A - int to array which gives consequent 1|0's lenghts
	// using 0len, 1len, 0len, 1len, ... go through array and merge adjacent 
	// values if the opposite value between them is len0 
	// (add 1's sequences if between len is 0)
	// O(b) time, O(b) memory, b = length of bits
	static ArrayList<Integer> getAlternateSequences(int n) {
		ArrayList<Integer> seq = new ArrayList<>();
		int bit = 0;
		int cnt = 0;
		
		for (int i = 0; i < Integer.BYTES * 8; i++) {
			if ((n & 1) != bit) {
				seq.add(cnt);
				bit = n & 1;
				cnt = 0;
			} else {
				cnt++;
				n >>>= 1;
			}
		}
		seq.add(cnt);
		return seq;
	}
	
	static int findLongestSeq(ArrayList<Integer> seq) {
		int max = 1;
		
		for (int i = 0; i < seq.size(); i += 2) {
			int zeros = seq.get(i);
			int onesRight = (i - 1) >= 0 ? seq.get(i - 1) : 0;
			int onesLeft = (i + 1) < seq.size() ? seq.get(i + 1) : 0;
			
			int sq = 0;
			if (zeros == 1) // can merge
				sq = onesLeft + zeros + onesRight;
			else if (zeros > 1) // just add a zero to either side 
				sq = 1 + Math.max(onesRight, onesLeft);
			else if (zeros == 0) // no need for zero, only one part
				sq = Math.max(onesRight, onesLeft);
			max = Math.max(sq, max);
		}
		
		return max;
	}
	
	public static int longestSequenceA(int n) {
		// if all ones, that's the longest
		if (n == -1) 
			return Integer.BYTES * 8;
		ArrayList<Integer> seq = getAlternateSequences(n);
		return findLongestSeq(seq);
	}
	
	// VERSION B - enough to compare each 1seq to next 1seq, keep current
	// and previous. If next bit 0 -> update previousLenght;
	// If next bit 1 -> previousLenght = currentLenght
	// If next bit 0 -> cannot merge, previousLenght = 0
	public static int longestSequenceB(int n) {
		//  if all ones, that's the longest
		if (~n == 0)
			return Integer.BYTES * 8;
		
		int currentLength = 0;
		int previousLength = 0;
		int max = 1; // always seq of 1 at least
		while (n != 0) {
			if ((n & 1) == 1) // current bit is 1
				currentLength++;
			else { // current bit is 0
				// update: if next bit = 0 ? update to 0 : update to currentLenght
				previousLength = (n & 10) == 0 ? 0 : currentLength;
				currentLength = 0;
			}
			max = Math.max(previousLength + currentLength + 1, max);
			n >>>= 1;
		}
		return max;
	}
	
	public static void main(String args[]) {
		// System.out.println(longestSequenceA(1775)); //11011101111 -> 8
		// System.out.println(longestSequenceA(11)); //1011 -> 4
		
		System.out.println(longestSequenceB(1775)); //11011101111 -> 8
		System.out.println(longestSequenceB(11)); //1011 -> 4
		System.out.println(longestSequenceB(9)); //1001 -> 2
	}
}
