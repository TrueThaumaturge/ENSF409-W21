package edu.ucalgary.ensf409;

public class ArgFileNotFoundException extends Exception {
	public ArgFileNotFoundException() {
		super("Error: argument file not found.");
	}
}