package in.misk.route;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

final class CountingAggregationStrategy implements AggregationStrategy {
	@Override
	public Exchange aggregate(final Exchange arg0, final Exchange arg1) {
		Integer count = Integer.valueOf(0);
		if (arg0 != null) {
			count = arg0.getIn().getBody(Integer.class);
		}
		count = Integer.valueOf(count.intValue() + 1);
		arg1.getOut().setBody(count);
		return arg1;
	}
}