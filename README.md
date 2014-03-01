House Data
==========

Investigation into using [Apache Camel][1] to host a web service and [backbone.js][2] to access it. 
This follows on from the work in [rest-example](https://github.com/rpmiskin/rest-example).

This is a learning project, using data from [data.gov](http://data.gov.uk) to create a webservice that deals with large amounts of data.
There is a camel route that loads data into an embedded database and a RESTful webservice that provides access to the data.
The webservice allows paging, query terms and ordering of results.

The intention is to use [backbone.pageable](https://github.com/backbone-paginator/backbone-pageable) and 
[Backgrid.js](http://backgridjs.com) to provide tabular access to the data.

[1]: https://camel.apache.org
[2]: http://backbonejs.org
