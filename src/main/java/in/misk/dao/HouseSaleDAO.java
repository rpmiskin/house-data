package in.misk.dao;

import in.misk.dao.entities.HouseSaleEntity;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO for handling HouseSale objects.
 * 
 * <p>
 * Makes use of Spring transactions as configured by the context.
 * </p>
 * 
 */
@Transactional(readOnly = false)
@Component
public class HouseSaleDAO {
	/** The logger instance. */
	private static Logger LOGGER = Logger.getLogger(HouseSaleDAO.class);

	/**
	 * The session factory used for access to all database access.
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @return all TodoEntity objects from the database
	 */
	public int count() {
		final Criteria countCriteria = criteria().setProjection(
				Projections.rowCount());
		return ((Integer) countCriteria.list().get(0)).intValue();
	}

	/**
	 * @return all TodoEntity objects from the database
	 */
	public HouseSaleEntity[] get(final int pageSize, final int pageNum,
			final String orderBy) {

		// Prevent attempts to get results less that 0.
		final int firstResult = Math.max(0, pageSize * (pageNum - 1));
		// Prevent attempts to get less that 1.
		final int maxResults = Math.max(1, pageSize);
		final Criteria pagedCriteria = criteria().setMaxResults(maxResults)
				.setFirstResult(firstResult);
		String order = "saleDate";
		if (orderBy != null) {
			order = orderBy;
		}
		final List<?> findByCriteria = pagedCriteria.addOrder(Order.asc(order))
				.list();

		LOGGER.info("Found " + findByCriteria.size() + " todos.");
		throw new UnsupportedOperationException("Test of transaction");
		// return findByCriteria.toArray(new HouseSaleEntity[0]);
	}

	/**
	 * 
	 * @param id
	 *            the id of the TodoEntity to find
	 * @return the TodoEntity with the supplied id, or <code>null</code>
	 */
	public HouseSaleEntity get(final String id) {
		HouseSaleEntity retVal = null;
		final List<?> found = criteria().add(Restrictions.idEq(id)).list();
		if (found.size() == 1) {
			retVal = (HouseSaleEntity) found.get(0);
		} else {
			if (found.isEmpty()) {

				LOGGER.info("No elements found for id" + id);
			} else {
				LOGGER.error("Multiple elements found for id" + id);
			}
		}
		return retVal;
	}

	/**
	 * 
	 * @param input
	 *            an update to persist
	 * @return <code>true</code> if the update is successful, otherwise
	 *         <code>false</code>
	 */
	public boolean update(final HouseSaleEntity input) {
		boolean success = false;
		if (exists(input)) {
			try {
				sessionFactory.getCurrentSession().merge(input);
				success = true;
			} catch (final DataAccessException e) {
				LOGGER.error("Error updating entity: " + input.getId(), e);
			}
		} else {
			LOGGER.error("No entity with supplied id exists.");
		}
		return success;
	}

	/**
	 * 
	 * @param input
	 *            an entity to persist
	 * @return <code>true</code> if the persist is successful, otherwise
	 *         <code>false</code>
	 */
	public boolean add(final HouseSaleEntity input) {
		boolean success = false;

		if (!exists(input)) {
			try {
				sessionFactory.getCurrentSession().save(input);
			} catch (final DataAccessException e) {
				LOGGER.error("Error saving entity with id" + input.getId(), e);
			}
			success = true;
		} else {
			LOGGER.warn("Entity with id " + input.getId() + " already exists.");
		}
		return success;
	}

	/**
	 * @param id
	 *            the id of the entity to remove.
	 * @return the entity that has been removed, or <code>null</code> if no
	 *         entity with the supplied id exists.
	 */
	public HouseSaleEntity remove(final String id) {
		final HouseSaleEntity e = get(id);
		if (e != null) {
			sessionFactory.getCurrentSession().delete(e);
		}
		return e;
	}

	/** Checks if an entity with the same id already exists in the database. */
	private boolean exists(final HouseSaleEntity input) {
		return !criteria().add(Restrictions.idEq(input.getId())).list()
				.isEmpty();
	}

	/** @return a criteria that can be used to look up TodoEntity objects. */
	private Criteria criteria() {
		return sessionFactory.getCurrentSession().createCriteria(
				HouseSaleEntity.class);
	}
}
