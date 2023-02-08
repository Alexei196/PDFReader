package cs635Assignment4;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class FlyweightStrategy implements ArrayCreatorStrategy {
	private PDFTextStripper textStripper;
	private ArrayList<PDFData> heldArray;
	private RunArray fontRunsArray;
	private CharacterFlyweightFactory characterFlyweightFactory;
	private FontFlyweightFactory fontFlyweightFactory;
	/**Default Constructor of the flyweight's strategy. Creates the text stripper and flyweight
	 * factories. To acquire font information of the characters, one must override the 
	 * processTextPosition function PDFBox calls on each character. This creates an awkward 
	 * situation that prevents information hiding of the held array in the scope of a function.
	 */
	FlyweightStrategy() {
		this.characterFlyweightFactory = new CharacterFlyweightFactory();
		this.fontFlyweightFactory = new FontFlyweightFactory();
		this.textStripper = new PDFTextStripper() {
			@Override
			protected void processTextPosition(TextPosition eachCharacter) {
				CharacterFlyweight characterFlyweight = characterFlyweightFactory.getFlyweight(
						eachCharacter.getUnicode().charAt(0));
				Font fontFlyweight = fontFlyweightFactory.getFlyweight(
						eachCharacter.getFont().getName(),
						eachCharacter.getFontSizeInPt(), 
						eachCharacter.getFont().getFontDescriptor().getFlags());
				heldArray.add(new PDFFlyweightCharacter(characterFlyweight, fontFlyweight));
			}
		};
		this.textStripper.setLineSeparator("");
	}
	
	@Override
	public ArrayList<PDFData> createStoredObjectArray(PDDocument pdfText) {
		heldArray = new ArrayList<PDFData>();
		try {
			textStripper.getText(pdfText);
			fontRunsArray = new RunArray(heldArray);
		} catch (IOException e) {
			heldArray = null;
		}
		return heldArray;
	}
	/**Accessor method for the run array created by the createStoredObjectArray method, 
	 * since the runarray requires the text to be stripped to be made.
	 * @return the run array associated with the strategy's storedObjectArray.
	 */
	public RunArray getRunArray() {
		return fontRunsArray;
	}
	/**Gives the amount of flyweights created by the font and character factories 
	 * @return integer amount of flyweights created by strategy.
	 */
	public int getFlyweightAmount() {
		return characterFlyweightFactory.getCacheLength() + fontFlyweightFactory.getCacheLength();
	}
}
