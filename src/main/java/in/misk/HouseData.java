package in.misk;

import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

public class HouseData {

	/**
	 * @param args
	 */
	public static void main(final String[] args) throws IOException {
		// TODO Auto-generated method stub
		final CSVReader reader = new CSVReader(new FileReader(
				"/Users/richard/Downloads/pp-2009.csv"));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			System.out.println(nextLine.length + "  " + nextLine[0]
					+ nextLine[1] + "etc...");
		}
	}

}
