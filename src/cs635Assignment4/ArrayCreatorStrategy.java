package cs635Assignment4;

import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;

public interface ArrayCreatorStrategy {
	/**Creates the object array representation of the given pdf text using the 
	 * method given by an implementing solution.
	 * @param pdfText pdf document that will be converted to equivalent data array
	 * @return ArrayList containing each pdf's character data of unicode and font
	 */
	public ArrayList<PDFData> createStoredObjectArray(PDDocument pdfText);
}
