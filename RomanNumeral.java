import java.util.Comparator;
import java.util.HashMap;

/**
 * 
 * @author Jason Cheng
 * @version 5/4/2023
 */
public class RomanNumeral implements Comparator<RomanNumeral> {
	private int Arabic;
	String Roman;

	/**
	 * To return the Arabic values and not a memory address.
	 */
	public String toString() {
		return Roman + " " + Arabic;
	}

	/**
	 * Makes a default constructor
	 */
	public RomanNumeral() {

	}

	/**
	 * Returns the Roman Numeral values.
	 * @return
	 */
	public String getRoman() {
		return Roman;
	}

	/**
	 * Constructor that gets the Roman numeral value, and convert it into Arabic, throws an exception if a string value passed into the constructor returns the value of -1.
	 * @param r
	 */
	public RomanNumeral(String r) {
		this.Roman = r;
		//Check to see if each roman numeral returns the value -1 through roman method. 
		if (romantoint(r) == -1) //If the value is -1, an exception will be thrown.
			throw new IllegalRNException("This is bad: " + r);

		Arabic = romantoint(r); //Convert RomanNumeral into arabic and store it into the integer.

	}

	/**
	 * Return method for Arabic values, also is used for the compareTo method to sort the RomanNumeral values.
	 * @return Arabic
	 */
	public int getArabic() {
		return Arabic;
	}

	/**
	 * compareTo method used for TreeMap to sort the Arabic values.
	 */
	public int compareTo(RomanNumeral o) {
		int result = 0;
		if (this.Arabic < o.getArabic()) {
			result = -1;
		} else if (this.Arabic > o.getArabic()) {
			result = 1;
		} else {
			return 0;
		}
		return result;
	}

	/**
	 * Method that converts Roman Numeral into arabic.
	 * @param input
	 * @return
	 */
	public static int romantoint(String input) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>(); //Using hashmap to set the Arabic value to the Roman Numeral.
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		//For loop to see if the value we get from the hashmap is null, then will return -1 and check the next string.
		for (int i = 0; i < input.length(); i++) {
			if (map.get(input.charAt(i)) == null)
				return -1;
		}

		// Converts Roman integers to Arabic integers
		int len = input.length();
		int result = map.get(input.charAt(len - 1));
		for (int i = len - 2; i >= 0; i--) {
			if (map.get(input.charAt(i)) >= map.get(input.charAt(i + 1)))
				result += map.get(input.charAt(i));
			else
				result -= map.get(input.charAt(i));

		}
		return result;

	}

	/**
	 * Comparator method for the treemap. Treemap compares two data with the compareTo method.
	 */
	@Override
	public int compare(RomanNumeral o1, RomanNumeral o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}

}
