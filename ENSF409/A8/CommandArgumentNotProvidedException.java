package edu.ucalgary.ensf409;

public class CommandArgumentNotProvidedException extends Exception {
	public CommandArgumentNotProvidedException() {
		super("Error: a command-line argument must be provided.");
	}
}