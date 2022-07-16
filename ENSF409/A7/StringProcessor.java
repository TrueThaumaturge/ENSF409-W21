// Quentin Jennings
// 30089570
// Assignment 7, 2021-03-08

package edu.ucalgary.ensf409;

import java.util.regex.*;

public class StringProcessor{
    
    private final String storedString;
    
    public StringProcessor(String input){
        this.storedString = new String(input);
    }
    
    public String addTogetherMirror(String inputString) {
        String combined = storedString.trim() + inputString.trim();
		StringBuilder mirrored = new StringBuilder(combined).reverse();
        return mirrored.toString();
    }

    public static String idProcessing(String firstName, String lastName, String petName, int year) {
        firstName = firstName.trim();
		lastName = lastName.trim();
		petName = petName.trim();
		
		if(firstName.length() < 2 || firstName.length() > 26 || 
				lastName.length() < 2 || lastName.length() > 26 || 
				petName.length() < 2 || petName.length() > 26) {
			throw new IllegalArgumentException("Name inputs need to be between 2-26 characters.");
		}

		if(firstName.charAt(0) < 65 || firstName.charAt(0) > 90 || 
				(firstName.charAt(firstName.length() - 1) < 65 ||
				firstName.charAt(firstName.length() - 1) > 90) &&
				(firstName.charAt(firstName.length() - 1) < 97 || 
				firstName.charAt(firstName.length() - 1) > 122)) {
			throw new IllegalArgumentException("First name must start with a capital letter and end with a letter.");
		}
		
		if(lastName.charAt(0) < 65 || lastName.charAt(0) > 90 || 
				(lastName.charAt(lastName.length() - 1) < 65 ||
				lastName.charAt(lastName.length() - 1) > 90) &&
				(lastName.charAt(lastName.length() - 1) < 97 || 
				lastName.charAt(lastName.length() - 1) > 122)) {
			throw new IllegalArgumentException("Last name must start with a capital letter and end with a letter.");
		}
		
		if(petName.charAt(0) < 65 || petName.charAt(0) > 90 || 
				(petName.charAt(petName.length() - 1) < 65 ||
				petName.charAt(petName.length() - 1) > 90) &&
				(petName.charAt(petName.length() - 1) < 97 || 
				petName.charAt(petName.length() - 1) > 122)) {
			throw new IllegalArgumentException("Pet name must start with a capital letter and end with a letter.");
		}
		
		if(year < 1000 || year > 2021) {
			throw new IllegalArgumentException("Year must be 4 digits and before 2022.");
		}
		
		Pattern invalidCharCheck = Pattern.compile("[^a-zA-Z'.\\- \\s]|['.\\- \\s]{2}");
		Matcher firstNameMatcher = invalidCharCheck.matcher(firstName);
		Matcher lastNameMatcher = invalidCharCheck.matcher(lastName);
		Matcher petNameMatcher = invalidCharCheck.matcher(petName);
		if(firstNameMatcher.find()) {
			throw new IllegalArgumentException("First name must not contain invalid characters.");
		}
		if(lastNameMatcher.find()) {
			throw new IllegalArgumentException("Last name must not contain invalid characters.");
		}
		if(petNameMatcher.find()) {
			throw new IllegalArgumentException("Pet name must not contain invalid characters.");
		}
		
        String petID = new String(String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0)) + String.valueOf(petName.charAt(0)) + String.valueOf(year));
        return petID;
    }

    public String secretCode(int offset) {
		StringBuilder encodedString = new StringBuilder();
		offset %= 26;
        for (int i = 0; i < storedString.length(); i++){
			int newUnicode = storedString.charAt(i) + offset;
			if(Character.isUpperCase(storedString.charAt(i))) {
				if(newUnicode > 'Z') {
					newUnicode -= 26;
				}
				else if(newUnicode < 'A') {
					newUnicode += 26;
				}
				encodedString.append((char)newUnicode);
			}
			else if (Character.isLowerCase(storedString.charAt(i))) {
				if(newUnicode > 'z') {
					newUnicode -= 26;
				}
				else if(newUnicode < 'a') {
					newUnicode += 26;
				}
				encodedString.append((char)newUnicode);
			}
			else {
				encodedString.append((char)storedString.charAt(i));
			}
        }
        
        return encodedString.toString();
    }
    
    public String getStoredString(){
        return this.storedString;
    }

}