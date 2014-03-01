package in.misk.route;

import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Camel route to expose a REST webservice within the embedded jetty container.
 * 
 */
@Component
public class DataIngestRoute extends SpringRouteBuilder {

	@Autowired
	CSVSplitter readLine;

	@Autowired
	CSVPersister persist;

	@Override
	public void configure() throws Exception {
		//@formatter:off
		from("file:/Users/richard/git/house-data/input")
		.log("${header.CamelFileAbsolutePath}")
		// Convert the File body into an iterator
		.bean(readLine)
		// Split so each body is a CSV line
		.split(body(), counter()).streaming().parallelProcessing()
			.bean(persist)
			.log("${body}")
		.end()
		.log("TotalCount: ${body}");
		// @formatter:on
	}

	private AggregationStrategy counter() {
		return new CountingAggregationStrategy();
	}

}
