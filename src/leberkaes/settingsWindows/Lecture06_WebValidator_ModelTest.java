package leberkaes.settingsWindows;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Lecture06_WebValidator_ModelTest {
	private static String[] validPortNumbers = {"1", "17", "8080", "65535"}; 
	private static String[] invalidPortNumbers = {"-12345", "0", "65536", "1000000", "Not a number", "12.5"}; 
	private static String[] validWebAddresses = {"abc.com", "www.abc.com", "ralph.jones.abc.com", "127.0.0.1", "1.0.0.1", "255.255.255.255"}; 
	private static String[] invalidWebAddresses = {"com", "a.cd", "a.b", "1.2.3", "1.2.3.4.5", "-1.2.3.4", "1.256.3.4"}; 

	private static WebValidator_Model model = new WebValidator_Model();;

	@Test
	public void validPortNumber_test() {
		for (String pn : validPortNumbers) {
			assertTrue(model.isValidPortNumber(pn));
		}
	}

	@Test
	public void invalidPortNumber_test() {
		for (String pn : invalidPortNumbers) {
			assertFalse(model.isValidPortNumber(pn));
		}
	}

	@Test
	public void validWebAddress_test() {
		for (String pn : validWebAddresses) {
			assertTrue(model.isValidWebAddress(pn));
		}
	}

	@Test
	public void invalidWebAddress_test() {
		for (String pn : invalidWebAddresses) {
			assertFalse(model.isValidWebAddress(pn));
		}
	}
}
