package cs635Assignment4;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		File sampleFile = new File("sampleText.pdf");
		System.out.printf("Flyweights: All Character/Font Flyweights shared: %b\n", Tests.testResultOfFlyweightStrategy(sampleFile));
		System.out.printf("No Flyweights: All Character/Font Data not shared: %b\n", Tests.testResultOfNoFlyweightStrategy(sampleFile));
		System.out.printf("Result of Investigation: %s\n", Tests.getResultOfFlyweightInvestigator(sampleFile));
	}
	/* Output from commandLine is :
	 * Flyweights: All Character/Font Flyweights shared: true
	 * No Flyweights: All Character/Font Data not shared: true
	 * Result of Investigation: The Flyweight saved 157336 bytes of memory!
	 */
}
