package in.misk.route;

import in.misk.dao.HouseSaleDAO;
import in.misk.dao.entities.HouseSaleEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CSVPersister {

	@Autowired
	HouseSaleDAO dao;

	public String process(final String[] line) {
		final StringBuilder str = new StringBuilder();
		for (final String s : line) {
			str.append(s);
			str.append(", ");
		}
		return str.toString();
	}

	public String process(final HouseSaleEntity sale) {
		dao.add(sale);
		final StringBuilder str = new StringBuilder(sale.getId());
		str.append(" ");
		str.append(sale.getPrice());
		return str.toString();
	}
}
