import java.lang.reflect.InvocationTargetException;
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
		if (maxValue < 1)
			throw new IllegalArgumentException("maxValue must be atleast 1");
		
		String invalidInputMessage = String.format("Please enter a number between 1 and %s.", maxValue);
		if (maxValue == 1)
			invalidInputMessage = "Please enter 1.";
				
		Scanner sc = new Scanner(System.in);
		int number = -1;
		boolean isValidInput = false;
		
		while (!isValidInput) {
			System.out.println(prompt);
			
			String input = sc.nextLine();
			try {
				number = Integer.parseInt(input);
			} catch (NumberFormatException e) {
			} finally {
				if (number >= 1 && number <= maxValue) {
					isValidInput = true;
				} else {
					System.out.println(invalidInputMessage);
				}
			}
		}

		return number;
	}
	
	
	/**
	 * Takes a given class as a class object and returns a new instance of that
	 * class.
	 * Only works for classes with no argument constructors.
	 * @param className		The class to be instantiated as a class object.
	 * @return				A new instance of the given class.
	 * 						Returned Object needs to be cast to the appropriate type.
	 */
	public static Object instantiate(Class<?> className) {
		Object newInstance = null;
		try {
			newInstance = className.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return newInstance;
	}
	
	
	public static void main(String[] args) {
		Util.getIntFromUser(5, "Enter number");
	}
	
}
