package in.ponram.util;

import in.ponram.exception.UtilException;

public class NumberValidator {

	private NumberValidator() {
		// Default Constructor
	}

	/**
	 * This method used to check quantity and rate should greater than zero
	 * 
	 * @param value
	 * @return true whether the value is greater then zero
	 */
	public static boolean isValidNumber(int value, String message) {

		if (value <= 0) {
			throw new UtilException(message);
		} else {

			return true;
		}
	}

	/**
	 * This method is used to convert the string into integer
	 * 
	 * @param input
	 * @param errorMessage
	 * @return
	 */
	public static int parseInt(String input, String errorMessage) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new UtilException(errorMessage);
		}
	}

	/**
	 * This method is used to convert the string into Long
	 * 
	 * @param input
	 * @param errorMessage
	 * @return
	 */
	public static long parseLong(String input, String errorMessage) {
		try {
			return Long.parseLong(input);
		} catch (NumberFormatException e) {
			throw new UtilException(errorMessage);
		}
	}

}
