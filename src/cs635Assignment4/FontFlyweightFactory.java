package cs635Assignment4;

import java.util.ArrayList;

public class FontFlyweightFactory {
	private ArrayList<Font> existingFontCache;
	/**Default Constructor for font flyweight factory, which initializes the existing font cache 
	 */
	FontFlyweightFactory(){
		existingFontCache = new ArrayList<Font>();
	}
	/**Checks if the font cache already contains a font flyweight and returns it if so. If not, 
	 * then a new flyweight is made, added, then returned. 
	 * @param fontName name of given font
	 * @param pointSize size in points of character
	 * @param style integer flags of given font. 
	 * @return Flyweight from the existing cache that represents the given font. 
	 */
	public Font getFlyweight(String fontName, float pointSize, int style) {
		Font potentialFont = new Font(fontName, pointSize, style);
		if(! existingFontCache.contains(potentialFont)) {
			existingFontCache.add(potentialFont);
		}
		return existingFontCache.get(existingFontCache.indexOf(potentialFont));
	}
	/**Gives the amount of entries to the flyweight cache
	 * @return integer amount of entries in flyweight cache.
	 */
	public int getCacheLength() {
		return existingFontCache.size();
	}
	
	//Overridden toString method that gives string representation of existing font cache.
	public String toString() {
		return existingFontCache.toString();
	}
}
