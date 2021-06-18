package in.ponram.util;

import in.ponram.exception.UtilException;

public class StringValidator {

	private StringValidator() {
		// Default constructor
	}

	/**
	 * This method used to check string should not empty and null
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isValidString(String value, String message) {

		if (value == null || value.trim().equals("")) {

			throw new UtilException(message);
		} else {

			return true;
		}
	}

}
