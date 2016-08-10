/**
* Converts number into words (American English).
* @author Siken Man Singh Dongol
**/
public class NumberToWords {

	private static String[] Ones = { "",
			"one", "two", "three", "four", "five",
			"six", "seven", "eight", "nine" };

	private static String[] Tens = { "", "",
			"twenty", "thirty", "forty", "fifty",
			"sixty", "seventy", "eighty", "ninty" };

	private static String[] FixTen = { "", "", "", "", "", "", "", "", "", "",
			"ten", "eleven", "twelve", "thirteen", "fourteen",
			"fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };

	private static String[] MoneyVal = { "",
			"thousand, ", "million, ", "billion, ", "trillion, ", "quadrillion ",
			"quintillion, ", "sextillion, ", "septillion, ", "octillion, ",
			"nonillion, ", "decillion, "};

	public static String Convert(int in) {
		return Convert(Integer.toString(in));
	}

	public static String Convert(String in) {
		String out = "";
		int block, Q, R;	// Quotient & Remainder
		int inLen = in.length();

		Q = inLen / 3;		// important
		R = inLen % 3;

		if (R == 0) {
			block = Q;
		} else {
			block = Q + 1;
		}

		int X = 0;	// X increases by 3, as there is difference of 3 digits in million, billion and so on
		for (int i = block; i >= 1; i--) {
			if (i == block && R > 0) {
				out = Hundred(in.substring(0, R)) + " " + MoneyVal[i - 1];
			} else {
				out += Hundred(in.substring(R + X, R + X + 3)) + " " + MoneyVal[i - 1];
				X = X + 3;
			}
		}
		// System.out.println(out);
		return out.trim();
	}

	// Method that gives Hundred in words
	// eg. 532 => Five Hundred Thirty Two
	private static String Hundred(String in) {
		int mVal;
		String out = "";

		String tmpStr;
		String tenStr, oneStr;

		int strLen = in.length();

		if (strLen > 3)
			return "";

		if (strLen == 3) {
			tmpStr = in.substring(0, 1);
			if(Integer.parseInt(tmpStr)!=0) {	// to deal leading 0's in the hundred eg 053 or 003
				out = Ones[Integer.parseInt(tmpStr)] + " hundred ";
			}
		}

		// Common to all
		if (strLen == 3) {
			tmpStr = in.substring(1);
		} else {
			tmpStr = in; // important
		}

		try {
			mVal = Integer.parseInt(tmpStr);
			if (mVal >= 0 && mVal < 10) {
				out = out + Ones[mVal];
			} else if (mVal > 9 && mVal < 20) {
				out = out + FixTen[mVal];
			} else {
				if (strLen == 3) {
					tenStr = in.substring(1, 2);
				} else {
					tenStr = in.substring(0, 1);
				}

				if (strLen == 3) {
					oneStr = in.substring(2, 3);
				} else {
					oneStr = in.substring(1, 2);
				}

				if(Integer.parseInt(oneStr)==0){
					out = out + Tens[Integer.parseInt(tenStr)];
				} else {
					out = out + Tens[Integer.parseInt(tenStr)] +"-"+ Ones[Integer.parseInt(oneStr)];
				}
			}
		} catch (Exception e) {
			System.out.println("Please enter a valid number.");
		}

		return out;
	}

	public static void main(String[] args) {
		for(int i=1; i<56789;i=i+234){
			System.out.println(i+": "+NumberToWords.Convert(i));
		}

		System.out.println("467332: "+NumberToWords.Convert("467332"));
		System.out.println("2343219: "+NumberToWords.Convert("2343219"));
		System.out.println("46793431: "+NumberToWords.Convert("46793431"));
		System.out.println("2567843719: "+NumberToWords.Convert("2567843719"));

		System.out.println(NumberToWords.Convert("345a6"));
		System.out.println(NumberToWords.Hundred("533"));
	}
}
