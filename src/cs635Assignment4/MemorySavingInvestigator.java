package cs635Assignment4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
/**Algorithm for checking the memory usage of various pdf reading strategies.
 * Used in examining the memory savings of the flyweight pattern. 
 * @author Andrew Rossman
 * @since 12.9.22
 * @version 1.0
 */
public class MemorySavingInvestigator {
	final Runtime runtime = Runtime.getRuntime();
	private ArrayCreatorStrategy memoryStrategy;
	private PDDocument samplePDF;
	/**Constructor that initializes investigator with target pdf file as parameter. Since no
	 * strategy is given, the default NoFlyweight is used.  
	 * @param targetFile pdf file used to investigate the flyweight's memory savings. 
	 * @throws IOException thrown if the file cannot find/read given target file.
	 */
	MemorySavingInvestigator(File targetFile) throws IOException{
		this.memoryStrategy = new NoFlyweightStrategy();
		samplePDF = Loader.loadPDF(targetFile);
	}
	/**Constructor that loads both target pdf file and strategy passed as parameter.  
	 * @param targetFile pdf file used to investigate the flyweight's memory savings. 
	 * @param memoryStrategy Memory strategy of how data is stored from the PDF.
	 * @throws IOException thrown if the file cannot find/read given target file.
	 */
	MemorySavingInvestigator(File targetFile, ArrayCreatorStrategy selectedStrategy) throws IOException{
		this.memoryStrategy = selectedStrategy;
		this.samplePDF = Loader.loadPDF(targetFile);
	}
	/**Calculates the memory usage of the memory strategy by comparing the data used by runtime 
	 * before and after creating an object with selected strategy. 
	 * @return long of the amount of memory used in the memory operation
	 */
	public long calulateMemoryUsage() {
		runtime.gc();
		long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
		@SuppressWarnings("unused")
		ArrayList<PDFData> newData = memoryStrategy.createStoredObjectArray(samplePDF);
		long afterMemory = runtime.totalMemory() - runtime.freeMemory();
		
		return afterMemory - beforeMemory;
	}
	/**Swaps the current strategy to another given by parameter.
	 * @param newStrategy Strategy to be swaped to.
	 */
	public void changeStrategy(ArrayCreatorStrategy newStrategy) {
		memoryStrategy = newStrategy;
	}
}
