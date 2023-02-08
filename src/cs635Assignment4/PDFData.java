package cs635Assignment4;

public interface PDFData {
	/**Font accessor method for the character data found in the pdf
	 * @return Font of the character data from the pdf 
	 */
	public Font getFont();
	/**Unicode character accessor method for the character data foun in the pdf 
	 * @return Unicode of the character data from the pdf
	 */
	public char getUnicode();
	/**Turns the PDFData into a string equivalent using the implementing class' fields. 
	 * @return string representation of implementing object.
	 */
	public String toString();
}
