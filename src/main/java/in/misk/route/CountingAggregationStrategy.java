package in.misk.route;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.log4j.Logger;

/**
 * Aggregation strategy that counts the number of exchanges that have been
 * processed and stores it in the exchange body.
 */
final class CountingAggregationStrategy implements AggregationStrategy {
	private final Logger log;

	public CountingAggregationStrategy(final Logger log) {
		this.log = log;
	}

	@Override
	public Exchange aggregate(final Exchange arg0, final Exchange arg1) {
		Integer count = Integer.valueOf(0);
		if (arg0 != null) {
			count = arg0.getIn().getBody(Integer.class);
		}
		count = Integer.valueOf(count.intValue() + 1);
		arg1.getOut().setBody(count);

		if (0 == (count.intValue() % 1000)) {
			log.info("Items processed: " + count);
		}

		return arg1;
	}
}