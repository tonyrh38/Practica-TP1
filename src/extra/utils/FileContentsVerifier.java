package extra.utils;

import invaders.game.Game;
import invaders.game.Level;

public class FileContentsVerifier {
	
	public static final String separator1 = ";";
	public static final String separator2 = ",";
	public static final String labelRefSeparator = " - ";

	private String foundInFileString = "";
	
	private void appendToFoundInFileString(String linePrefix) {
		foundInFileString += linePrefix;
	}

	// Don't catch NumberFormatException.
	public boolean verifyCycleString(String cycleString) {
		String[] words = cycleString.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 2 || !verifyCurrentCycle(Integer.parseInt(words[1]))) return false;
		else return true;
	}
	
	public boolean verifyLevelString(String levelString) {
		String[] words = levelString.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 2 || !verifyLevel(Level.valueOf(words[1]))) return false;
		else return true;
	}
	
	// Don't catch NumberFormatException.
	public boolean verifyOvniString(String lineFromFile, Game game, int armour) {
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 3) return false;
		String[] coords = words[1].split(separator2);
		if (!verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLives(Integer.parseInt(words[2]), armour)) 
			return false;
		else return true;
	}

	// Don't catch NumberFormatException.
	public boolean verifyPlayerString(String lineFromFile, Game game, int armour) {
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 6) return false;

		String[] coords = words[1].split(separator2);
		
		if (!verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLives(Integer.parseInt(words[2]), armour)
				|| !verifyPoints(Integer.parseInt(words[3]))
				|| !verifyBool(words[4]))
			return false;
		else return true;
	}
	
	// Don't catch NumberFormatException.
	public boolean verifyAlienShipString(String lineFromFile, Game game, int armour) {
		String[] words = lineFromFile.split(separator1);
		appendToFoundInFileString(words[0]);
		if (words.length != 5 && words.length != 6) return false;

		String[] coords = words[1].split(separator2);
		
		if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
				|| !verifyLives(Integer.parseInt(words[2]), armour)
				|| !verifyCycleToNextAlienMove(Integer.parseInt(words[3]), game.getLevel())
				|| !verifyDir(words[4])) {
			return false;
		}
		else return true;
	}
	
	// Don't catch NumberFormatException.
	public boolean verifyWeaponString(String lineFromFile, Game game) {
		String[] words = lineFromFile.split(separator1);
		if (words.length != 2 && words.length != 3) return false;
		
		appendToFoundInFileString(words[0]);
		String[] coords = words[1].split(separator2);

		if ( !verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game) )
			return false;
		else return true;
	}
	
	public boolean verifyRefString(String lineFromFile) {
		String[] words = lineFromFile.split(labelRefSeparator);
		if (words.length != 2 || !verifyLabel(words[1])) return false;
		else return true;
	}

	public static boolean verifyLabel(String label) {
		return Integer.parseInt(label) > 0;
	}
	
	public static boolean verifyCoords(int x, int y, Game game) {
		return game.isOnBoard(x, y);
	}
	
	public static boolean verifyCurrentCycle(int currentCycle) {
		return currentCycle >= 0;
	}
	
	public static boolean verifyLevel(Level level) {
		return level != null;
	}
	
	public static boolean verifyDir(String dir) {
		return dir.equals("left") || dir.equals("right");
	}
	
	public static boolean verifyLives(int live, int armour) {
		return 0 < live && live <= armour;
	}
	
	public static boolean verifyPoints(int points) {
		return points >= 0;
	}
	
	public static boolean verifyCycleToNextAlienMove(int cycle, Level level) {
		return 0 <= cycle && cycle <= level.getVel();
	}
	
	// parseBoolean converts any string different from "true" to false.
	public static boolean verifyBool(String boolString) {
		return boolString.equals("true") || boolString.equals("false");
	}

	public boolean isMissileOnLoadedBoard() {
		return foundInFileString.toUpperCase().contains("L");
	}
	
	// Use a regular expression to verify the string of concatenated prefixes found
	public boolean verifyLines() {
		//compare foundInFileString with a regular expression
		return true; 
	}

	// text explaining allowed concatenated prefixes
	public String toString() {
		return "";
	}
}
