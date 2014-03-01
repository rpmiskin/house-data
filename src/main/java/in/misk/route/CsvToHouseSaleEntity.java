package in.misk.route;

import in.misk.dao.entities.HouseSaleEntity;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

/**
 * Convert a line to the entity.
 * 
 * @author richard
 * 
 */
public class CsvToHouseSaleEntity extends CsvToBean<HouseSaleEntity> {
	private final ColumnPositionMappingStrategy<HouseSaleEntity> strat;

	public CsvToHouseSaleEntity() {
		strat = new ColumnPositionMappingStrategy<HouseSaleEntity>();
		strat.setType(HouseSaleEntity.class);
		strat.setColumnMapping(new String[] { "id", "price", "dateStr",
				"postCode", null, null, null });
	}

	public HouseSaleEntity processLine(final String[] line)
			throws IllegalAccessException, InvocationTargetException,
			InstantiationException, IntrospectionException {
		return processLine(strat, line);
	}
}