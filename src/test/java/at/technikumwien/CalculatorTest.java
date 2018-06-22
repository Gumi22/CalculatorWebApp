package at.technikumwien;

import org.junit.Before;

import at.technikumwien.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculatorTest {
	private Calculator calculator;
	
	@Before
	public void setUp() {
		calculator = new Calculator();		
	}

	@Test
	public void testNullReference(){
		assertEquals("Calculator should return 0 on null reference",0, calculator.sum(null));
	}

	@Test
	public void testSingleNumber(){
		assertEquals("Calculator should return same number",1, calculator.sum(1));
	}

	@Test
	public void testMultipleNumbers(){
		assertEquals("Calculator should return correct sum of numbers", 10, calculator.sum(1,2,3,4));
	}
}