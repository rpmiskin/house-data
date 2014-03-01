package in.misk.route;

import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Camel route read a CSV and persist the contained records.
 * 
 */
@Component
public class DataIngestRoute extends SpringRouteBuilder {

	Logger log = Logger.getLogger(getClass().getSimpleName());

	@Autowired
	private CSVSplitter readLine;

	@Autowired
	private CSVPersister persist;

	@Override
	public void configure() throws Exception {
		//@formatter:off
		from("file:input")
		.routeId(getClass().getSimpleName())
		// Log the name of the file being processed.
		.log("${header.CamelFileAbsolutePath}")
		// Convert the File body into an iterator
		.bean(readLine)
		// Split so each body is a CSV line
		.split(body(), new CountingAggregationStrategy(log)).streaming().parallelProcessing()
			.bean(persist)
		.end()
		.log("TotalCount: ${body}");
		// @formatter:on
	}

}
