/**
* JavaStrings is a simple class that uses the built-in String function
* to perform various utilities.
* @author	 Quentin Jennings <a href="mailto:quentin.jennings@ucalgary.ca">
*	quentin.jennings@ucalgary.ca</a>
* @version	1.2
* @since	1.0
*/
public class JavaStrings {
	
	/*
	//Main used for testing, not needed in submission
	public static void main(String[] args)
	{
		JavaStrings test = new JavaStrings();
		System.out.print(test.addTogether("hey","yoooo") + "\n"
			+ test.idProcessing("John","Alsojohn","Picklerick",1984) + "\n"
			+ test.secretCode("soap") + " || " + test.secretCode("bananas"));
	}
	*/
	
	/**
	 * Trims the whitespace off both input strings then combines them
	 * and returns the length of the combined string.
	 * @param str1 One of two strings to be combined.
	 * @param str2 One of two strings to be combined.
	 * @return int Returns the length of the combined string.
	 */
	public int addTogether(String str1, String str2) {
		str1.trim();
		str2.trim();
		String combinedStr = new String();
		combinedStr += str1 + str2;
		return combinedStr.length();
	}
	
	/**
	 * Creates an ID string composed of the initials of a given first name,
	 * last name, pet name, and year.
	 * @param firstName The owner's first name.
	 * @param lastName The owner's last name.
	 * @param petName The pet's first name.
	 * @param year The year of birth of the pet.
	 * @return String Returns the identifier as a string.
	 */
	public String idProcessing(String firstName, String secondName, String petName, int year) {
		String id = new String();
		id += firstName.charAt(0);
		id += secondName.charAt(0);
		id += petName.charAt(0);
		id += String.valueOf(year);
		return id;
	}
	
	/**
	 * Encodes a string with a secret code that reduces a given ingredient
	 * name to its first 3 letters and replaces vowels with a z.
	 * @param ing The name of the ingredient to be encoded (assumed at least 3 letters long).
	 * @return String Returns the encoded ingredient.
	 */
	public String secretCode(String ing){
		String encodedIng = new String();
		encodedIng = ing.substring(0,3);
		encodedIng = encodedIng.toLowerCase(); //converts all to lowercase for replacement
		encodedIng = encodedIng.replace('a','z');
		encodedIng = encodedIng.replace('e','z');
		encodedIng = encodedIng.replace('i','z');
		encodedIng = encodedIng.replace('o','z');
		encodedIng = encodedIng.replace('u','z');
		return encodedIng;
	}
}	