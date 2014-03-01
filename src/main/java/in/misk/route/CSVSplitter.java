package in.misk.route;

import in.misk.dao.entities.HouseSaleEntity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVReader;

@Component
public class CSVSplitter {

	public static class CSVIterator implements Iterator<HouseSaleEntity> {
		final CSVReader reader;
		final CsvToHouseSaleEntity toBean = new CsvToHouseSaleEntity();
		int count;

		String[] next;

		public CSVIterator(final File f) throws IOException {
			reader = new CSVReader(new FileReader(f));

			next = reader.readNext();
		}

		@Override
		public boolean hasNext() {
			// return next != null;
			// Swap for the following line to stop at 5 lines.
			// return count < 5 && (next != null);
			return count < 10000 && (next != null);
		}

		@Override
		public HouseSaleEntity next() {
			count++;
			if (next == null) {
				throw new NoSuchElementException();
			}
			HouseSaleEntity toReturn;
			try {
				toReturn = toBean.processLine(next);
				try {
					next = reader.readNext();
				} catch (final IOException e) {
					next = null;
				}
			} catch (final Exception e1) {
				Logger.getLogger(getClass())
						.error("Failure during mapping", e1);
				next = null;
				throw new NoSuchElementException();
			}
			return toReturn;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Cannot remove from CSVIterator");
		}
	}

	public Iterator<HouseSaleEntity> getIterator(final File file)
			throws IOException {
		return new CSVIterator(file);
	}

}
