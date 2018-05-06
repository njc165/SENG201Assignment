import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

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
	 * An overloaded version of getIntFromUser which allows a minValue to be
	 * specified as well as a max value.
	 * Repeatedly asks the user for input using the given prompt until a valid integer
	 * between minValue and given maxValue inclusive is entered.
	 * @param minValue  The lowest integer which should be accepted as valid input.
	 * @param maxValue	The highest integer which should be accepted as valid input.
	 * @param prompt	The prompt message which asks the user for input.
	 * @return			The valid integer entered by the user.
	 */
	public static int getIntFromUser(int minValue, int maxValue, String prompt) {
		if (maxValue < 1)
			throw new IllegalArgumentException("maxValue must be atleast 1");
		
		String invalidInputMessage = String.format("Please enter a number between %s and %s.",
														minValue, maxValue);
		if (maxValue == minValue)
			invalidInputMessage = String.format("Please enter %s.", minValue);
				
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
				if (number >= minValue && number <= maxValue) {
					isValidInput = true;
				} else {
					System.out.println(invalidInputMessage);
				}
			}
		}

		return number;
	}
	
	
	/**
	 * Asks the user the given question and gives them the option to choose yes
	 * or no.
	 * @param question	The yes or no question to ask the user.
	 * @return	true if the user answers Yes, false if the user answers No.
	 */
	public static boolean getYesNo(String question) {
		System.out.println(question);
		System.out.println("1. Yes\n2. No\n");
		int choice = Util.getIntFromUser(2, "Enter your choice:");
		return choice == 1;
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
	
	
	/**
	 * Takes a ButtonGroup of JRadioButtons and returns the selected button.
	 * @param buttonGroup	The relevant ButtonGroup.
	 * @return				The currently selected button in the ButtonGroup.
	 */
	public static JRadioButton selectedButton(ButtonGroup buttonGroup) {
		JRadioButton selectedButton = null;
		for (Enumeration<AbstractButton> e = buttonGroup.getElements(); e.hasMoreElements();) {
			JRadioButton button = (JRadioButton) e.nextElement();
			if (button.isSelected()) {
				selectedButton = button;
			}
		}
		return selectedButton;
	}
	
}
