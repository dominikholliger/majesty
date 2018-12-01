package leberkaes.settingsWindows;

public class GameSettings_Model {
	
	protected boolean isValidPortNumber(String newValue) {
		boolean valid = true;

		try {
			int value = Integer.parseInt(newValue);
			if (value < 1 || value > 65535) valid = false;
		} catch (NumberFormatException e) {
			// wasn't an integer
			valid = false;
		}

		return valid;
	}
}
