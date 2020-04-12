package Bit_Manipulation;

public class Q8_DrawLine {
	
	// VERSION A - just iterate through and pixelate, well this is very inefficient
	
	// VERSION B - there is a possibility to walk 'grouped' using bytes w/ 0xFF, and
	// fill the gaps at beggining and end
	void drawLineB(byte[] screen, int width, int x1, int x2, int y) {
		int start_offset = x1 % 8;
		int start = x1 / 8;
		
		if (start_offset != 0)
			start++;
		
		int end_offset = x2 % 8;
		int end = x2 / 8;
		
		if (end_offset != 7)
			end--;
		
		// set full bytes
		for (int b = start; b <= end; b++) {
			screen[(width / 8) * y + b] = (byte) 0xFF;
		}
		
		// create mask for start & end, may need them
		byte st = (byte) (0xFF >> start_offset);
		byte nd = (byte) (0xFF << (end_offset + 1));
		
		// start & end
		if ((x1 / 8) == (x2 / 8)) // see whether equal
		{
			byte mask = (byte) (st & nd);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			if (start_offset != 0) {
				int bNumber = (width / 8) * y + start - 1;
				screen[bNumber] |= st;
			}
			if (end_offset != 7) {
				int bNumber = (width / 8) * y + end + 1;
				screen[bNumber] |= nd;
			}
		}
	}
	
	// main comes here
}
