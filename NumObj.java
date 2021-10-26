package numbers;

class NumObj {
	final private long number;
	final private boolean BUZZ;
	final private boolean DUCK;
	final private boolean PALINDROMIC;
	final private boolean GAPFUL;
	final private boolean SPY;
	final private boolean EVEN;
	final private boolean ODD;
	
	public NumObj(final long number) {
		this.number = number;
		BUZZ = isBuzzNumber(number, 7L);
		DUCK = isDuckNumber(number);
		PALINDROMIC = isPalindromic(number);
		GAPFUL = isGapful(number);
		SPY = isSpyNum(number);
		if(isEven(number)) {
			EVEN = true;
			ODD = false;
		} else {
			EVEN = false;
			ODD = true;
		}
	}
	
	void printProperties() {
		System.out.print("\t" + number + " is ");
		if(BUZZ) {System.out.print("buzz, ");}
		if(DUCK) {System.out.print("duck, ");}
		if(PALINDROMIC) {System.out.print("palindromic, ");}
		if(GAPFUL) {System.out.print("gapful, ");}
		if(SPY) {System.out.print("spy, ");}
		if(EVEN) {System.out.print("even\n");} else {System.out.print("odd\n");}
	}
	
	boolean isProperty(String property) {
		switch(property.toLowerCase()) {
			case "buzz": return BUZZ;		
			case "duck": return DUCK;
			case "palindromic": return PALINDROMIC;
			case "gapful": return GAPFUL;
			case "spy": return SPY;
			case "even": return EVEN;
			case "odd": return ODD;
			default:
				return false;
		
		}
	}
	
	
	
	static boolean isBuzzNumber(long number, long divisor) {
		if(number <= 0) {
//			System.out.println("This number is not natural!");
			return false;
			}
//		oddOrEven(number);
		String num = String.valueOf(number);
		char lastNum = num.charAt(num.length()-1);
		boolean sevIsLast = lastNum == '7';
		boolean divSev = (number % divisor) == 0;
		if(sevIsLast && divSev) {
//			System.out.println("It is a Buzz number\nExplanation:");
//			System.out.println(number + " is divisible by 7 and ends with 7.");
			return true;
		} else if(sevIsLast) {
//			System.out.println("It is a Buzz number\nExplanation:");
//			System.out.println(number + " ends with 7.");
			return true;
		} else if(divSev) {
//			System.out.println("It is a Buzz number\nExplanation:");
//			System.out.println(number + " is divisible by 7.");
			return true;
		} else {
//			System.out.println("It is not a Buzz number\nExplanation:");
//			System.out.println(number + "is neither divisible by 7 nor does it end with 7.");
			return false;
		}
	}

	static boolean isDuckNumber(long number) {
		String num = String.valueOf(number);
		boolean isNonZero = false;
		for (int i = 0; i < num.length(); i++) {
			if(num.charAt(i) != '0') {
				isNonZero = true;
			}
			if(isNonZero) {
				if(num.charAt(i) == '0') {
					return true;
				}
			}
		}
		return false;
	}

	static boolean isNaturalNumber(long number) {
		if (number <= 0) {
			//System.out.println("\nThe first parameter should be a natural number or zero\n");
			return false;
		}
		return true;
	}
	
	static boolean isEven(long number) {
		if((number & 1) == 1) {
			return false;
		}
		return true;
	}
	
	static boolean isPalindromic(long number) {
		String num = String.valueOf(number);
		for (int i = 0; i < num.length()/2; i++) {
			if(num.charAt(i) != num.charAt(num.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	static boolean isGapful(long number) {
		if(number < 100) {return false;}
		String num = String.valueOf(number);
		char firstChar = num.charAt(0);
		char lastChar = num.charAt(num.length()-1);
		String concat = String.valueOf(firstChar) + String.valueOf(lastChar);
		long div = (long) Integer.parseInt(concat);
		if(number % div == 0) {
			return true;
		} else {
			return false;
		}	
	}
	
	static boolean isSpyNum(long number) {
		char[] nums = ("" + number).toCharArray();
		long sum = 0L;
		long product = 1L;
		for(int i = 0; i < nums.length; i++) {
			try {
				sum += Character.getNumericValue(nums[i]);
				product *= Character.getNumericValue(nums[i]);
			} catch (Exception e) {
				return false;
			}
		}
		return (sum == product);
	}
}
