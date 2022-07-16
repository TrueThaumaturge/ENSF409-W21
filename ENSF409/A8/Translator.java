package edu.ucalgary.ensf409;

import java.util.regex.*;
import java.io.*;

	public class Translator {
		private String lang;
		private TranslationText translation;
		
		
		 /* Constructor
		   * Accepts a String of a two-letter language code, dash, and two-letter region
		   * code, e.g., te-IN and throws an IllegalArgumentException if the language and
		   * region code are not in the correct format. Language codes are ISO 639-1 and
		   * region codes are ISO 3166, but this method only checks the format of the String, 
		   * not the validity of the codes. It calls importTranslation().
		  */
		public Translator(String lang) throws ArgFileNotFoundException {
			Pattern myPatt = Pattern.compile("[a-z]{2}-[A-Z]{2}");
			Matcher myMatch = myPatt.matcher(lang);
			if(!(myMatch.matches())) {
				throw new IllegalArgumentException("Illegal argument: \"" + lang + "\" is not in the correct xx-YY format.");
			}
			this.lang = lang;
			importTranslation();
		}
		
		/* translate()
		   * Accepts a month number (e.g., 1 for January), a day number (e.g., 31 for
		   * the 31st), and a year. Throws an IllegalArgumentException if monthNum or dayNum is 
		   * not valid. Returns the formatted sentence as a String. Notice that the String
		   * containing formatting uses numbered arguments - this is because some languages
		   * will put the words in the sentence in a different order, but the translate()
		   * method must be able to work without knowledge of the language structure.
		   * Note: You do not have to check if a day is valid for a particular month/year;
		   * February 31 or February 29, 2021 can both be accepted, but out of range values
		   * e.g., month 15 day 40, are not valid and should be handled with an 
		   * IllegalArgumentException. 
		  */
		public String translate(int month, int day, int year) {
			if(month < 1 || month > 12 || day < 1 || day > 31) {
				throw new IllegalArgumentException("Error: illegal day or month input. (must be 1-31 and 1-12)");
			}
			return String.format(translation.getSentence(), translation.getDay(day-1), translation.getMonth(month-1), year);
			
		}
		
		  /* importTranslation()
		   * Calls deserialize() if the appropriate file exists, otherwise calls importFromText().
		   * No arguments. Returns void.
		  */
		public void importTranslation() throws ArgFileNotFoundException {
			File serializedFile = new File(lang + ".ser");
			if(serializedFile.exists()) {
				deserialize();
			}
			else {
				importFromText();
			}
		}
		
		  /* importFromText()
		   * Reads in from a the two-letter language code, dash, two-letter region code text 
		   * file, in the form of ab-XY.txt, and instantiates a TranslationText object with
		   * the data. It can throw I/O exceptions. Throw a custom ArgFileNotFoundException
		   * when the file isn't found. 
		   * We expect the .txt file to be in a valid format. The file is expected to be in the same 
		   * directory. The files en-US.txt and el-GR.txt are examples of a valid .txt files. They will 
		   * always consist of the month names, one per line, followed by the day names, one per line, 
		   * followed by the sentence containing formatting strings. This is the last line in the file. You
		   * cannot make any assumptions about what will appear on each line, only that each line
		   * will contain only one data element, and that it will not contain an empty line.
		   * No arguments. Returns void.
		  */
		public void importFromText() throws ArgFileNotFoundException {
			File textFile = new File(lang + ".txt");
			if(!(textFile.exists())) {
				throw new ArgFileNotFoundException();
			}
			else {
				BufferedReader input = null;
				try {
					input = new BufferedReader(new FileReader(textFile));
					
					String[] months = new String[12];
					for(int i = 0; i < 12; i++) {
						months[i] = new String(input.readLine());
					}
					String[] days = new String[31];
					for(int i = 0; i < 31; i++) {
						days[i] = new String(input.readLine());
					}
					String sentence = new String(input.readLine());
					
					translation = new TranslationText(months, days, sentence);
				}
				catch(IOException e) {
					System.err.println("Error: IOException occured reading file " + lang + ".txt.");
					System.exit(1);
				}
				finally {
					if(input != null) {
						try {
							input.close();
						}
						catch(IOException e) {
							System.err.println("Error: IOException occured closing file " + lang + ".txt.");
							System.exit(1);
						}
					}
				}
			}
		}
		
		  /* serialize()
		  * Creates a serialized object file of the TranslationText object, with the
		  * name format la-CO.ser, where la is the two-letter language code and CO is
		  * the two-letter region code. An example of a serialized object file can be
		  * found in the exercise directory as es-BO.ser
		  * I/O exceptions can be thrown.
		  * No arguments. Returns void.
		  */
		public void serialize() {
			ObjectOutputStream output = null;
			try {
				output = new ObjectOutputStream(new FileOutputStream(new File(lang + ".ser")));
			}
			catch(IOException e) {
				System.err.println(lang + ".ser.");
				System.exit(1);
			}
			
			try {
				output.writeObject(translation);
			}
			catch(Exception e) {
				System.err.println("Error: Exception occured when serializing " + lang + " file.");
				e.printStackTrace();
			}
			finally {
				try {
					if(output != null) {
						output.close();
					}
				}
				catch(IOException e) {
					System.err.println("Error: IOException occured when closing " + lang + ".ser.");
					System.exit(1);
				}
			}
		}
		
		 /* deserialize()
		  * Creates a TranslationText object from a .ser file. The files are named
		  * xx-YY.ser, where xx is the two-letter language code and YY is the two-
		  * letter region code. es-bo.ser is an example. It can throw I/O exceptions.
		  * No arguments. Returns void.
		  */
		public void deserialize() {
			ObjectInputStream input = null;
			try {
				input = new ObjectInputStream(new FileInputStream(new File(lang + ".ser")));
			}
			catch(IOException e) {
				System.err.println("Error: Could not open file " + lang + ".ser.");
				System.exit(1);
			}
			
			TranslationText newTranslation = null;
			try {
				newTranslation = (TranslationText) input.readObject();
				translation = newTranslation;
			}
			catch(Exception e) {
				System.err.println("Error: Exception occured when deserializing " + lang + " file.");
				e.printStackTrace();
			}
			finally {
				try {
					if(input != null) {
						input.close();
					}
				}
				catch(IOException e) {
					System.err.println("Error: IOException occured when closing " + lang + ".ser.");
					System.exit(1);
				}
			}
		}
		
		public TranslationText getTranslation() {
			return translation;
		}
	}
