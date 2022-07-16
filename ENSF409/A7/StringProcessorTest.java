package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

public class StringProcessorTest {
    @Test
    public void addTogetherMirrorTest1() {
        StringProcessor test = new StringProcessor(" Test ");
		String result = test.addTogetherMirror(" Test2");
		String expResult = new String("2tseTtseT");
        assertEquals(expResult, result);
    }
	
	@Test
    public void idProcessingTest1() {
        StringProcessor test = new StringProcessor("fuck it crab");
		String result = test.idProcessing("Quentin","A","O'-'Malley", 1969);
		String expResult = "CUM1969";
		assertEquals(expResult, result);
    }
	
	@Test
    public void secretCodeTestNeg() {
        StringProcessor test = new StringProcessor("fUCK it CrAaBz@!.");
		String result = test.secretCode(0); //0
		String expResult = "meh";
		assertEquals(expResult, result);
    }
}