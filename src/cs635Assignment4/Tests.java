package cs635Assignment4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.pdfbox.Loader;

public class Tests {
	/**Tests the memory investigator by subtracting the memory usage of the no flyweight strategy 
	 * with the memory usage of the flyweight strategy. Since most data is created in the 
	 * strategy class, we only need to pass in a new strategy to reset the investigator.
	 * @param sampleFile File object of read pdf File
	 * @return String whose contents explain the results of the memory saving calculator
	 */
	static String getResultOfFlyweightInvestigator(File sampleFile) {
		try {
			MemorySavingInvestigator investigator = new MemorySavingInvestigator(sampleFile, new NoFlyweightStrategy());
			long memoryDifference = investigator.calulateMemoryUsage();
			investigator.changeStrategy(new FlyweightStrategy());
			memoryDifference -= investigator.calulateMemoryUsage();
			return String.format("The Flyweight saved %d bytes of memory!\n", memoryDifference);
		} catch (IOException e) {
			return String.format("MemorySavingInvestigator test failed: %s\n", e.getMessage());
		}
	}
	/**Tests the flyweight strategy's factories to see if each result array's indexes 
	 * shares data locations with other indexes using Flyweights. Compares result array
	 * with fonts in run array and existing character flyweight cache from the character factory.
	 * @param targetFile file to grab pdf info from
	 * @return true if the pdfdata all share character and font flyweights, false otherwise
	 */
	static boolean testResultOfFlyweightStrategy(File targetFile) {
		try {
			FlyweightStrategy flyingStrategy = new FlyweightStrategy();
			ArrayList<PDFData> storeArray = flyingStrategy.createStoredObjectArray(Loader.loadPDF(targetFile));
			HashSet<Character> uniqueCharacterSet = new HashSet<>();
			HashSet<Font> uniqueFontSet = new HashSet<>();
			for(PDFData data : storeArray) {
				uniqueFontSet.add(data.getFont());
				uniqueCharacterSet.add(data.getUnicode());
			}
			return (uniqueCharacterSet.size() + uniqueFontSet.size()) == flyingStrategy.getFlyweightAmount();
		} catch (Exception e) {
			return false;
		}
	}
	/**Tests whether the array created by the no flyweight strategy contains duplicates. We know 
	 * flyweights were not used if all indexes each have a unique font and character
	 * Since characters can only be unicode when used by factory, 
	 * we only need to check for duplicate fonts.
	 * @param targetFile file to grab pdf info from
	 * @return true if all data retain a unique value, false otherwise.
	 */
	static boolean testResultOfNoFlyweightStrategy(File targetFile) {
		try {
			NoFlyweightStrategy flyingStrategy = new NoFlyweightStrategy();
			ArrayList<PDFData> storeArray = flyingStrategy.createStoredObjectArray(Loader.loadPDF(targetFile));
			HashSet<Font> uniqueFontSet = new HashSet<>();
			storeArray.forEach( (data)-> uniqueFontSet.add(data.getFont()) );
			return uniqueFontSet.size() == storeArray.size();
		} catch (Exception e) {
			return false;
		}
	}
}