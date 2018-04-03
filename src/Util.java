import java.util.Scanner;

public class Util {
	
	/**
	 * Repeatedly asks the user for input using the given prompt until a valid integer
	 * between 1 and the given maxValue inclusive is entered.
	 * @param maxValue	The highest integer which should be accepted as valid input.
	 * @param prompt	The prompt message which asks the user for input.
	 * @return			The valid integer entered by the user.
	 */
	public static int getIntFromUser(int maxValue, String prompt) {
		Scanner sc = new Scanner(System.in);
		int number = -1;
		boolean isValidInput = false;

		while (!isValidInput) {
			System.out.println(prompt);
			
			if (sc.hasNextInt()) {
				number = sc.nextInt();
				if (number >= 1 && number <= maxValue) {
					isValidInput = true;
				}
			} else {
				sc.next();
			}
			if (!isValidInput)
				System.out.println(String.format("Please enter a number between 1 and %s.",
						maxValue));
		}
		return number;
	}
	
	public static void main(String[] args) {
		Util.getIntFromUser(5, "Enter number");
	}
	
}
