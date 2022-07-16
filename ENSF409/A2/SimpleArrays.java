import java.util.Arrays;

/**
* SimpleArrays is a class that comes with two tools to combine an array 
* of strings into a single string.
* @author	 Quentin Jennings <a href="mailto:quentin.jennings@ucalgary.ca">
*	quentin.jennings@ucalgary.ca</a>
* @version	1.3
* @since 	1.0
*/
public class SimpleArrays {
	private String[] strArray;
	
	/*
	public static void main(String[] args)
	{
		SimpleArrays test = new SimpleArrays("Test");
		String str1 = test.arrayConcat(); 
		String str2 = test.arrayConcat(2); 
		String str3 = test.arrayCrop(2,1);
		String str4 = test.arrayCrop(-1, 1);
		String str5 = test.arrayCrop(1, 4);
		String str6 = test.arrayCrop(2, 2);
		System.out.println(str1+"\n"+str2+"\n"+str3+"\n"+str4+"\n"+str5+"\n"+str6);
	}
	*/
	
	/**
	 * Constructor sets a field to an array of strings and 
	 * fills each index with an identical string.
	 * @param str The string to be inserted in each index (Defaults to "Hello, ENSF409").
	 */
	public SimpleArrays(String str) {
		strArray = new String[4];
		Arrays.fill(strArray, str);
	}
	public SimpleArrays() {
		strArray = new String[4];
		Arrays.fill(strArray, "Hello, ENSF 409");
	}
	
	/**
	 * Returns a string consisting of all the elements of the array from a
	 * provided index to the end of the array, separated by a # character.
	 * @param start_i The starting index to be used (Defaults to 0).
	 * @return String Returns the concatenated string.
	 */
	public String arrayConcat(int start_i) {	
		return stringCombine(Arrays.copyOfRange(strArray, start_i, strArray.length));
	}
	public String arrayConcat() {
		return stringCombine(Arrays.copyOfRange(strArray, 0, strArray.length));
	}
	
	/**
	 * Returns a cropped string consisting of all the elements between two
	 * inclusive indices specified, concatenated, separated by a # character.
	 * @param start_i The first index for the crop.
	 * @param end_i The final index for the crop.
	 * @return String Returns the cropped concatenated string if no input errors 
	 * 		occur, otherwise returns a brief description of the error.
	 */
	public String arrayCrop(int start_i, int end_i) {
		//input check - returns string if failed, swaps two if needed
		if(start_i < 0 || start_i >= strArray.length || 
				end_i < 0 || end_i >= strArray.length) {
			return "Fail";
		}
		else if(start_i > end_i) {
			int temp = start_i;
			start_i = end_i;
			end_i = temp;
		}
		else if(start_i == end_i) {
			return "Match";
		}
		
		return stringCombine(Arrays.copyOfRange(strArray, start_i, end_i + 1));
	}
	
	/**
	 * Combines all the elements of a given array of strings into one string,
	 * separating each index with a # character.
	 * @param sourceArr The source array to be combined.
	 * @return String Returns the combined string.
	 */
	private String stringCombine(String[] sourceArr) {
		String combinedStr = new String();
		for(int i = 0; i < sourceArr.length; i++) {
			combinedStr += sourceArr[i];
			if(i != sourceArr.length - 1) {
				combinedStr += "#";
			}
		}
		return combinedStr;
	}
	
}	