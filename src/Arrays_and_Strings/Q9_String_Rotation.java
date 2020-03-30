package Arrays_and_Strings;

public class Q9_String_Rotation {
	/*
	 * rotation means has a rotations point, separation and then concatenation on
	 * the opposite way around
	 */

	// see if there is a way of splitting s1 into x and y s. t. xy = s1 and yx = s2;
	// yx is part of xyxy. Given isSubstring function
	// O(n)
	public static boolean isRotation(String s1, String s2) {
		int len = s1.length();
		if (len == s2.length() && len > 0) {
			String s1s1 = s1 + s1;
			return isSubstring(s1s1, s2);
		}
		return false;
	}

	// O(lengthBig + lengthSmall)
	public static boolean isSubstring(String big, String small) {
		if (big.indexOf(small) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		String s1 = "alfabeta";
		String s2 = "fabetaal";
		System.out.println("Is \'" + s1 + "\' the rotation of \'" + s2 + "\'? Answer : " + isRotation(s1, s2));
	}
}
