package cs635Assignment4;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class NoFlyweightStrategy implements ArrayCreatorStrategy {
	private PDFTextStripper textStripper;
	private ArrayList<PDFData> heldArray;
	/**Default Constructor of the no flyweight strategy. Since data space is being tested
	 * for, the heldArray object is initialized at a later function call while the textStripper 
	 * is defined early.
	 */
	NoFlyweightStrategy(){
		this.textStripper = new PDFTextStripper() {
			@Override
			protected void processTextPosition(TextPosition eachCharacter) {
				heldArray.add(new PDFCharacter(
					eachCharacter.getUnicode().charAt(0),
					eachCharacter.getFont().getName(),
					eachCharacter.getFontSizeInPt(), 
					eachCharacter.getFont().getFontDescriptor().getFlags() ));
			}
		};
		textStripper.setLineSeparator("");
	}
	
	@Override
	public ArrayList<PDFData> createStoredObjectArray(PDDocument pdfText) {
		this.heldArray = new ArrayList<PDFData>();
		try {
			textStripper.getText(pdfText);
		} catch (IOException e) {
			heldArray = null;
		}
		return heldArray;
	}
}
