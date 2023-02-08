package cs635Assignment4;

import java.util.ArrayList;

public class RunArray {
	private ArrayList<Run> heldRunList;
	private int currentListIndex;
	/**Constructor for the Run Array that defines itself from an array list representation of
	 * the pdfText, with font and character information included.
	 * @param pdfText array list of information from PDF
	 */
	RunArray(ArrayList<PDFData> pdfText) {
		this.heldRunList = new ArrayList<Run>();
		currentListIndex = 0;
		
		Font currentFont = pdfText.get(0).getFont();
		int runDuration = 0;
		for(PDFData eachCharacter : pdfText) {
			if(!currentFont.equals(eachCharacter.getFont()) ) {
				appendRun(runDuration, currentFont);
				currentFont = eachCharacter.getFont();
				runDuration = 0;
			} else {
				runDuration = runDuration + 1;
			}
		}
		appendRun(runDuration, currentFont);
	}
	/**Adds a new run to the run array but first ensures that the space the potential run is requesting
	 * is not taken up by another run or if the heldRunList already contains given information. Returns
	 * whether or not the add operation was successful.
	 * @param runStart start index of added run
	 * @param runLength length of run to be added
	 * @param characterFont added run's font data
	 * @return true if the run was added, false otherwise
	 */
	public boolean addRun(int runStart, int runLength, Font characterFont) {
		if(getFont(runStart) == null && getFont(runStart+runLength-1) == null) {
			boolean addSuccessful = heldRunList.add(new Run(runStart, runLength-1, characterFont));
			currentListIndex = addSuccessful ? currentListIndex+runLength-1 : currentListIndex;
			return addSuccessful;
		}
		return false;
	}
	/**Appends a new run to the run array exactly where the current index left off, reusing the addRun
	 * method for its body.
	 * @param runLength length of run to be added.
	 * @param characterFont added run's Font.
	 * @return true if the run was added, false otherwise.
	 */
	public boolean appendRun(int runLength, Font characterFont) {
		return addRun(currentListIndex, runLength, characterFont);
	}
	/**Returns the Font held for the character present at index in the original pdftext string 
	 * from the runArray. Returns null if the Font cannot be found.
	 * @param characterIndex Index of character with font stored within run array.
	 * @return Font of character present at index.
	 */
	public Font getFont(int characterIndex) {
		for(Run eachRun : heldRunList) {
			if (eachRun.withinBounds(characterIndex)) {
				return eachRun.getFont();
			}
		}
		return null;
	}
	/**Checks the run array to see if it contains the hashcode of a given font, returning true if
	 * it does,  false otherwise.
	 * @param desiredFont font to be looked for 
	 * @return true if the run array contains font reference, false otherwise.
	 */
	public boolean hasFont(Font desiredFont) {
		for(Run eachRun : heldRunList) {
			if(eachRun.getFont() == desiredFont) {
				return true;
			}
		}
		return false;
	}
	
	//Overridden toString gives string of the run array list
	public String toString() {
		return heldRunList.toString();
	}
}
