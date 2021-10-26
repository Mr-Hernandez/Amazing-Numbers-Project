package numbers;

import java.util.Scanner;
import java.util.Arrays;

class Main {

	public static void main(String[] args) {
		 stage5();
	}
	
	static void intro() {
		System.out.println("Welcome to Amazing Numbers!");
		System.out.println("\n\nsupported requests:\n"
				+ "- enter a natural number to know its properties;\n"
				+ "- enter two natural numbers to obtain the properties of the list:\n"
				+ "  * the first parameter shows how many consecutive number are to be printed;\n"
				+ "  * the second paramter shows how many consecutive numbers are tobe printed;\n"
				+ "- two natural numbers and a property to search for;"
				+ "- separate the parameters with one space;\n"
				+ "- enter 0 to exit\n");
	}

	static boolean isExit(long number) {
		if(number == 0) {
			System.out.println("Goodbye!");
			System.exit(0);
			return true;
		}
		return false;
	}
	
	static String getUserInputString() {
		System.out.println("\nEnter a request:\n");
		Scanner userInput = new Scanner(System.in);
		try {
			//int input = userInput.nextInt();
			String input = userInput.nextLine();
			return input;
		} catch(Exception e) {
			System.out.println("input error in Main.getUserInput()");
			System.exit(1);
			return "Error";
		}	
	}

	static long[] parseUserInput(String rawInput) {
		String[] strSplit = rawInput.split(" ");
		long[] arrOut = new long[2];
		arrOut[0] = Long.parseLong(strSplit[0]);
		try {
			long iterate = Long.parseLong(strSplit[1]);
			arrOut[1] = iterate;
		} catch (Throwable t) {
			arrOut[1] = 0L;
		}
		
		return arrOut;
	}
	
	static boolean isValidInput(String rawInput) {
		if(rawInput == null || rawInput.isBlank()) {
			errorMsg(0);
			return false;
		}
		String[] strSplit = rawInput.split(" ");
		if (strSplit.length > 3) {
			System.out.println("Too many inputs");
			return false;
		}
		
		
		if(strSplit.length == 3) {
			String[] strSplit2 = new String[] {strSplit[0], strSplit[1]};
			for (int i = 0; i < strSplit2.length; i++) {
				for (int j = 0; j < strSplit2[i].length(); j++) {
					if(!Character.isDigit(strSplit2[i].charAt(j))) {
						if(i == 0) {errorMsg(1);}
						else if ( i > 0) {errorMsg(2);}
						return false;
					}
				}
			}
		} else {
			for (int i = 0; i < strSplit.length; i++) {
				for (int j = 0; j < strSplit[i].length(); j++) {
					if(!Character.isDigit(strSplit[i].charAt(j))) {
						if(i == 0) {errorMsg(1);}
						else if ( i > 0) {errorMsg(2);}
						return false;
					}
				}
			}
		}
		
		String[] numProperties = new String[] {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY"};
		if(strSplit.length == 3) {
			for (int i = 0; i < numProperties.length; i++) {
				if(strSplit[2].toUpperCase().equals(numProperties[i])) {
					return true;
				}	
			}
		} 
		if(strSplit.length == 3) {
			System.out.println("\nThe property " + Arrays.asList(strSplit[2]) + " is wrong.");
			errorMsg(3); 
			return false;
		}
		return true;
	}
		
	static void errorMsg(int i) {
		String[] numProperties = new String[] {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY"};
		switch (i) {
		case 0: intro();
		break;
		case 1: System.out.println("\nThe first parameter should be a natural number or zero.\n");
		break;
		case 2: System.out.println("\nThe second parameter should be a natural number.\n");
		break;
		case 3: System.out.println("Available properties: " + Arrays.asList(numProperties));
			
		}
	}

	static void singleInput(long i) {
		System.out.println("\nProperties of " + i);
		System.out.println(   "\teven: " + NumberTester.isEven(i) +
							"\n\todd:  " + !NumberTester.isEven(i) +
							"\n\tbuzz: " + NumberTester.isBuzzNumber(i, 7) +
							"\n\tduck: " + NumberTester.isDuckNumber(i) +
							"\n Palindromic: " + NumberTester.isPalindromic(i) +
							"\n      gapful: " + NumberTester.isGapful(i) +
							"\n\t spy: " + NumberTester.isSpyNum(i));
	}
	
	static void twoInput(long firstArg, long secondArg) {
		for (long i = firstArg; i < firstArg + secondArg; i++) {
			System.out.print("\t" + i + " is ");
			if(NumberTester.isBuzzNumber(i, 7)) {System.out.print("buzz, ");}
			if(NumberTester.isDuckNumber(i)) {System.out.print("duck, ");}
			if(NumberTester.isPalindromic(i)) {System.out.print("palindromic, ");}
			if(NumberTester.isGapful(i)) {System.out.print("gapful, ");}
			if(NumberTester.isSpyNum(i)) {System.out.print("spy, ");}
			if(NumberTester.isEven(i)) {System.out.print("even\n");} else {System.out.print("odd\n");}
		}
	}
	
	static boolean threeInput(long firstArg, long iteration, String property) {
		NumObj numObjArr = new NumObj(firstArg + iteration);
		if(numObjArr.isProperty(property)) {
			numObjArr.printProperties();
			return true;
		} else {
			return false;
		}
	}
	
	static boolean isThreeInputs(String rawInput) {
		if(rawInput.split(" ").length == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	static void stage5() {
		intro();
		String rawInput;
		while (true) {
			 rawInput = getUserInputString();
			 if(!isValidInput(rawInput)) {continue;}
			 long[] input = parseUserInput(rawInput);
			 if(isExit(input[0])) {break;}
			 if(!NumberTester.isNaturalNumber(input[0])) {continue;}
			 if(input[1] == 0) {
				 singleInput(input[0]);
				 continue;
			 } else {
				 if(!NumberTester.isNaturalNumber(input[1])) {continue;}
			 }
			 if(!isThreeInputs(rawInput)) {
				 twoInput(input[0], input[1]);
			 } else {
				 int found = 0;
				 long iterations = 0;
				 String property = rawInput.split(" ")[2];
				 while(found < input[1]) {
					 if (threeInput(input[0], iterations, property)) {found++; iterations++;}
					 else {iterations++;} 	
				 }
			 }
			 
		}
		
	}

	@Deprecated
	static long getUserInput() {
		System.out.println("\nEnter a request:\n");
		Scanner userInput = new Scanner(System.in);
		try {
			//int input = userInput.nextInt();
			long input = userInput.nextLong();
			return input;
		} catch(Exception e) {
			System.out.println("input error in Main.getUserInput()");
			System.exit(1);
			return -1;
		}	
	}
	
	@Deprecated
	static void stage2() {
		long i = getUserInput();
		if(!NumberTester.isNaturalNumber(i)) {return;}
		System.out.println("Properties of " + i);
		System.out.println(   "\teven: " + NumberTester.isEven(i) +
							"\n\todd:  " + !NumberTester.isEven(i) +
							"\n\tbuzz: " + NumberTester.isBuzzNumber(i, 7) +
							"\n\tduck: " + NumberTester.isDuckNumber(i));
		
	}
	
	@Deprecated
	static void stage3() {
		intro();
		while (true) {
			long i = getUserInput();
			if (isExit(i)) {break;}
			if(!NumberTester.isNaturalNumber(i)) {continue;}
			System.out.println("Properties of " + i);
			System.out.println(   "\teven: " + NumberTester.isEven(i) +
								"\n\todd:  " + !NumberTester.isEven(i) +
								"\n\tbuzz: " + NumberTester.isBuzzNumber(i, 7) +
								"\n\tduck: " + NumberTester.isDuckNumber(i) +
								"\n Palindromic: " + NumberTester.isPalindromic(i));
		}
		
		
	}
	
	@Deprecated
	static void stage4() {	
		intro();
		String rawInput;
		while (true) {
			 rawInput = getUserInputString();
			 if(!isValidInput(rawInput)) {continue;}
			 long[] input = parseUserInput(rawInput);
			 if(isExit(input[0])) {break;}
			 if(!NumberTester.isNaturalNumber(input[0])) {continue;}
			 if(input[1] == 0) {
				 singleInput(input[0]);
				 continue;
			 } else {
				 if(!NumberTester.isNaturalNumber(input[1])) {continue;}
			 }
			 // Know we have 2 inputs
			 twoInput(input[0], input[1]);
			 
			 
		}
	}
}
