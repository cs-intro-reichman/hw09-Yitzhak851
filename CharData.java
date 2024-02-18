/**
 * Represents a character data object.
 * A character data object has: char value, counter, and 2 probability fields.
 * f.x: CharData cd = new CharData('c');
 * it meanns that cd is a CharData object with the following fields:
 * (‘c’,1, 0, 0)
 * 
 */
public class CharData {
	char chr; // a character
	int count; // a counter
	double p; // a probability (number between 0 and 1)
	double cp; // a commulative probability (number between 0 and 1)

	/** Creates and initializes a character data object. */
	public CharData(char chr) {
		this.chr = chr;
		this.count = 1;
		this.p = 0;
		this.cp = 0;
	}

	/**
	 * Checks if the character of this CharData object equals the given character.
	 */
	public boolean equals(char chr) {
		return this.chr == chr;
	}

	/** Returns a textual representation of this CharData object. */
	public String toString() {
		return "( " + chr + " " + count + " " + p + " " + cp + ")";
	}
}