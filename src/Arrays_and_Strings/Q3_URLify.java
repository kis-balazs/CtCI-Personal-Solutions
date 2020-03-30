package Arrays_and_Strings;

public class Q3_URLify {
	/*
	 * character array, length given, enough space
	 */

	// count whitespaces, and rewrite array to fit everything, and starting from
	// bottom when finding space, just change to %20
	// O(n)
	public static void URLify(char[] str, int trueLength) {
		int spaceCount = 0, index, i = 0;
		for (i = 0; i < trueLength; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		index = trueLength + spaceCount * 2;
		if (trueLength < str.length)
			str[trueLength] = '\0';
		for (i = trueLength - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[index - 1] = '0';
				str[index - 2] = '2';
				str[index - 3] = '%';
				index -= 3;
			} else {
				str[index - 1] = str[i];
				index--;
			}
		}
	}

	// eliminate end-spaces
	// O(n)
	public static int findLastCharacter(char[] str) {
		for (int i = str.length - 1; i >= 0; i--) {
			if (str[i] != ' ') {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String str = "Valami alma korte szilva      ";
		char[] arr = str.toCharArray();
		int trueLength = findLastCharacter(arr) + 1;
		URLify(arr, trueLength);
		System.out.println(arr);
	}

}