package cs635Assignment4;

public class CharacterFlyweight {
	private char intrinsicCharacter;
	/**Constructor that assigns the flyweight's held character to a unicode value.
	 * @param intrinsicCharacter unicode character value to be stored in flyweight
	 */
	CharacterFlyweight(char intrinsicCharacter) {
		this.intrinsicCharacter = intrinsicCharacter;
	}
	/**Compares two character flyweights for equality. Returning true if they share unicode value,
	 * false othwise or if the otherObject's type does not match CharacterFlyweight
	 * @param otherObject object to compare this to
	 * @return true if otherObject matches unicode value, false otherwise.
	 */
	public boolean equals(Object otherObject) {
		try {
			return this.intrinsicCharacter == ((CharacterFlyweight) otherObject).intrinsicCharacter;
		} catch (ClassCastException e) {
			return false;
		}
	}
	/**Returns the unicode value held by this character flyweight 
	 * @return intrinsic character unicode shared by all referencing objects
	 */
	public char getUnicode() {
		return intrinsicCharacter;
	}
	
	//Overridden toString method that returns the string value of held unicode character
	public String toString() {
		return String.format("{'%c', HASH:%X}", intrinsicCharacter, this.hashCode());
	}
}
