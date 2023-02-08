package cs635Assignment4;

public class PDFFlyweightCharacter implements PDFData{
	private CharacterFlyweight characterFlyweight;
	private Font fontFlyweight;
	/**Constructor for the flyweight using conversion of a character from the PDF file. Since
	 * the font object itself is only a reference, it can be used as both flyweight and instance
	 * the only difference is how they are created by the factory method.
	 * @param characterFlyweight flyweight reference of the pdf character's unicode value
	 * @param fontFlyweight flyweight reference of the pdf character's given font
	 */
	PDFFlyweightCharacter(CharacterFlyweight characterFlyweight, Font fontFlyweight) {
		this.characterFlyweight = characterFlyweight;
		this.fontFlyweight = fontFlyweight;
	}
	
	@Override
	public Font getFont() {
		return fontFlyweight;
	}
	
	//Overridden method to create a string representation of the PDFFlyweightCharacter
	@Override
	public String toString() {
		return String.format("[%s,%s]", characterFlyweight.toString(), fontFlyweight.toString());
	}

	@Override
	public char getUnicode() {
		return characterFlyweight.getUnicode();
	}
}
