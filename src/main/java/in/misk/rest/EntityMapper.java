/**
 * 
 */
package in.misk.rest;

import in.misk.dao.entities.HouseSaleEntity;

/**
 * Provides methods required to map between database entities and objects to
 * send over REST.
 * 
 * @author richard
 * 
 */
public class EntityMapper {

	SaleDataPage getPage(final int size, final int index,
			final HouseSaleEntity[] data) {
		final SaleDataPage dto = new SaleDataPage();
		dto.setSales(fromEntity(data));
		dto.setPageIndex(index);
		dto.setPageSize(size);
		dto.setTotalPages(-1);
		return dto;
	}

	SaleData fromEntity(final HouseSaleEntity entity) {
		final SaleData dto = new SaleData();
		dto.setId(entity.getId());
		dto.setPostCode(entity.getPostCode());
		dto.setPrice(entity.getPrice());
		dto.setSaleDate(entity.getSaleDate());
		return dto;
	}

	SaleData[] fromEntity(final HouseSaleEntity[] entity) {
		final SaleData[] dto = new SaleData[entity.length];
		for (int i = 0; i < entity.length; i++) {
			dto[i] = fromEntity(entity[i]);
		}
		return dto;
	}
}
