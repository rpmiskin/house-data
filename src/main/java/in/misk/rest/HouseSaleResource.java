package in.misk.rest;

import in.misk.dao.HouseSaleDAO;
import in.misk.dao.entities.HouseSaleEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Rest service that provides CRUD access to Todo objects.
 * 
 * <p>
 * The service sends and receives JSON data.
 * </p>
 * 
 */
@Path("/housesale/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class HouseSaleResource {

	/** Static logger instance. */
	private static final Logger LOGGER = Logger
			.getLogger(HouseSaleResource.class);

	@Autowired
	private HouseSaleDAO dao;

	/** Mapper to convert between DAO and REST entities. */
	private final EntityMapper mapper = new EntityMapper();

	/**
	 * Example method to get multiple values. Shows use of a query param.
	 * 
	 * @param title
	 *            the title of models to find
	 * @return a Response containing JSON data.
	 */
	@GET
	@Path("sales")
	public Response getAllValues(@QueryParam("page") final int page,
			@QueryParam("pageSize") final int pageSize,
			@QueryParam("orderby") final String orderby) {
		LOGGER.info("Count=" + dao.count());
		final HouseSaleEntity[] all = dao.get(pageSize, page, orderby);
		LOGGER.info("getAllValues(" + page + ", " + pageSize + ", " + orderby
				+ "). Returned " + all.length + " values.");

		final SaleDataPage salePage = mapper.getPage(pageSize, page, all);

		return response(Status.OK).entity(salePage).build();
	}

	@OPTIONS
	@Path("sales")
	public Response getOptions() {
		return response(Status.OK).build();
	}

	@OPTIONS
	@Path("todos/{id}")
	public Response getOptions(@PathParam("id") final String ida) {
		return response(Status.OK).build();
	}

	/**
	 * Returns a specific model by id.
	 * 
	 * @param id
	 *            the of the matching model
	 * @return the request document as JSON
	 */
	@GET
	@Path("sales/{id}")
	public Response read(@PathParam("id") final String id,
			@Context final SecurityContext context) {
		ResponseBuilder response = null;
		final SaleData sale = mapper.fromEntity(dao.get(id));
		if (sale == null) {
			response = response(Status.NOT_FOUND);
		} else {
			response = response(Status.OK).entity(sale);
		}
		return response.build();
	}

	/**
	 * Create a response with the supplied status and add the necessary headers
	 * to enable CORS.
	 * 
	 * @param status
	 *            the status to use in the response
	 * @return a {@link ResponseBuilder} with the supplied status and headers to
	 *         enable CORS.
	 */
	private ResponseBuilder response(final Status status) {
		return Response
				.status(status)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods",
						"POST, GET, PUT, UPDATE, OPTIONS, DELETE")
				.header("Access-Control-Allow-Headers",
						"Content-Type, Accept, X-Requested-With");
	}
}
