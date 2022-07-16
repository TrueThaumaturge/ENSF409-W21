package edu.ucalgary.ensf409;
import java.io.*;

/* TranslationText
 * Serializable representation of the data file. Has the serialVersionUID of 19.
 * No method in this class throws an exception.
*/
public class TranslationText implements Serializable {
	static final long serialVersionUID = 19L;
	private String sentence;
	private String[] months = null;
	private String[] days = null;
	
	  /* Constructor
   * Accepts a String array of months, a String array of days, and a String 
   * containing a sentence with formatting.
  */
	public TranslationText(String[] months, String[] days, String sentence){
		this.months = months;
		this.days = days;
		this.sentence = sentence;
	}
	
	public String getSentence() {
		return sentence;
	}
	
	public String[] getMonths() {
		return months;
	}
	
	public String[] getDays() {
		return days;
	}
	
	public String getMonth(int i) {
		return months[i];
	}
	
	public String getDay(int i) {
		return days[i];
	}
}

