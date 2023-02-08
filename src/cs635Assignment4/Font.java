package cs635Assignment4;

public class Font {
	String fontName;
	int style;
	float pointSize;
	/**Constructor that takes in the assignment specified descriptors (name, size, and styles)
	 * required to describe a font. PDFBox gives all styles of the read text, but stores them inside
	 * of an integer in which each bit represents a true/false flag of each style. The Bold flag
	 * bit is true if the text is bold, the italic flag bit is true if the text is italic, and so on.
	 * @param fontName Name of font
	 * @param pointSize size of font in points
	 * @param style style flags of the text
	 */
	Font(String fontName, float pointSize, int style){
		this.fontName = fontName;
		this.pointSize = pointSize;
		this.style = style;
	}
	/**Overridden equals function that compares this Font to another Font object, returning true if
	 * they are the same, false otherwise.
	 * @param otherObject Font object to be compared to this font
	 * @return true if the two fonts are equal, false otherwise
	 */
	public boolean equals(Object otherObject) {
		try {
			Font otherFont = (Font) otherObject;
			return this.fontName.equals(otherFont.fontName) 
					&& this.style == otherFont.style
					&& this.pointSize == otherFont.pointSize;
		} catch (ClassCastException e) {
			return false;
		}
	}
	
	//Overridden toString function that converts contents of font to string with hashcode
	public String toString() {
		return String.format("{%s, %.2f, %d, HASH:%X}", fontName, pointSize, style, this.hashCode());
	}
}
