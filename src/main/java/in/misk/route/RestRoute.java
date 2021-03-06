package in.misk.route;

import in.misk.rest.HouseSaleResource;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Camel route to expose a REST webservice within the embedded jetty container.
 * 
 */
@Component
public class RestRoute extends SpringRouteBuilder {

    @Autowired
    private HouseSaleResource helloWorld;

    @Override
    public void configure() throws Exception {
        //@formatter:off
		from("jetty://http://0.0.0.0:9000?matchOnUriPrefix=true&filtersRef=cors-filter&handlers=file-handler")
		.to("cxfbean:houseSaleResource?providers=#json");
		// @formatter:on
    }
}
