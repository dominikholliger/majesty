package leberkaes.settingsWindows;

import leberkaes.abstractClasses.Model;

public class GameSettings_Model extends Model {
	
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

	public boolean isValidPlayerCountNumber(String newValue) {
		boolean valid = true;
		try {
			int value = Integer.parseInt(newValue);
			if ( value > 4||value<2) valid = false;
		} catch (NumberFormatException e) {
			// wasn't an integer
			valid = false;
		}
		return valid;
	}
}
