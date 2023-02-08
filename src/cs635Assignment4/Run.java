package cs635Assignment4;

public class Run {
	private int startIndex, endIndex;
	private Font runFont;
	/**Run Constructor that represents several indexes of characters that share a single font. 
	 * @param startIndex starting index of run
	 * @param runLength length of run, used to calculate run's ending index
	 * @param runFont font of each character in run
	 */
	Run(int startIndex, int runLength, Font runFont){
		this.startIndex = startIndex;
		this.endIndex = startIndex + runLength - 1;
		this.runFont = runFont;
	}
	/**Checks if a given index exists inside of the current run, returning true if it does, 
	 * false otherwise
	 * @param characterIndex index of character possible inside of current run
	 * @return true if index is inside run, false otherwise
	 */
	public boolean withinBounds(int characterIndex) {
		return characterIndex >= startIndex && characterIndex <= endIndex;
	}
	/**Gives the length of the currentrun from the run start and end. 
	 * @return length of this run in integer form.
	 */
	public int getRunLength() {
		return endIndex - startIndex + 1;
	}
	/**Gives the font found in the current run. 
	 * @return font held by all characters in this run
	 */
	public Font getFont() {
		return runFont;
	}
	
	//Overridden method that exports the current run to a string representation
	public String toString() {
		return String.format("{%d, %d, %s}", startIndex, endIndex, runFont.toString());
	}
}
