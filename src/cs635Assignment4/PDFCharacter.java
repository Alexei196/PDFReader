package cs635Assignment4;

public class PDFCharacter implements PDFData{
	private char textCharacter;
	private Font characterFont;
	/**Constructor for a new non flyweight character that connects a character and 
	 * its font from a pdf. To separate this character from a flyweight, a new instance of
	 * Font is created when this character is made.
	 * @param textCharacter unicode character of character from pdf
	 * @param fontName Name of font used by character from pdf
	 * @param pointSize font size of character from pdf
	 * @param fontStyle style flags of character from pdf
	 */
	PDFCharacter(char textCharacter, String fontName, float pointSize, int fontStyle) {
		this.textCharacter = textCharacter;
		this.characterFont = new Font(fontName, pointSize, fontStyle);
	}
	
	@Override
	public Font getFont() {
		return characterFont;
	}
	
	@Override
	public String toString() {
		return String.format("['%c',%s]", textCharacter, characterFont.toString());
	}

	@Override
	public char getUnicode() {
		return textCharacter;
	}
}
