package in.misk;

import static org.junit.Assert.assertEquals;
import in.misk.dao.entities.HouseSaleEntity;
import in.misk.route.CsvToHouseSaleEntity;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

public class CSVToBeanTest {

	private CsvToHouseSaleEntity csvToHouseSaleEntity;

	@Before
	public void setup() {
		csvToHouseSaleEntity = new CsvToHouseSaleEntity();
	}

	@Test
	public void firstTest() throws Exception {
		final String[] line = new String[] {
				"{84D77B53-1A2E-4520-B0BD-00000F2552CC}", "87000",
				"2009-01-09 00:00", "CF31 2NA", "S", "N", "F", "8", "",
				"EASTERLY CLOSE", "BRACKLA", "BRIDGEND", "BRIDGEND",
				"BRIDGEND", "A" };
		final HouseSaleEntity output = csvToHouseSaleEntity.processLine(line);
		assertEquals("Expect ID to be field 0 of input", line[0],
				output.getId());
		assertEquals("Expect price to be field 1 of input", line[1],
				Integer.toString(output.getPrice()));

		final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		assertEquals("Expect sale date to be field 2 of input", line[2],
				f.format(output.getSaleDate()));
		assertEquals("Expect postcode to be field 3 of input", line[3],
				output.getPostCode());
	}
}
