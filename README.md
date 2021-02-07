To run/test this service, we need to have a mock service which it calls.
Follow the steps below for running the mock service. Add dummy responses as required.

1. download jar from http://wiremock.org/docs/running-standalone/
2. run using :java -jar wiremock-standalone-2.27.2.jar
3. once jar is running, check the mappings on GET: http://localhost:8080/__admin
4. to add mapping, run post rquest with given body(sample, body should be string only)
   POST : http://localhost:8080/__admin/mappings/new
	{
    "request" :
    {
        "url" : "/allemployees",
        "method" : "GET"
    },
    "response" :
    {
        "status" : 200,
        "body":"[{\"name\":\"alex\",\"id\":\"001\",\"dept\":\"engineer\"},{\"name\":\"dave\",\"id\":\"002\",\"dept\":\"sales\"},{\"name\":\"smith\",\"id\":\"003\",\"dept\":\"accounts\"}]"
    }
	}
	
	
	to add another mapping
	{
    "request" :
    {
        "url" : "/employee",
        "method" : "POST"
    },
    "response" :
    {
        "status" : 201,
        "body":"Employee added."
    }
	}
	
	
	another example to match body of the request
	{
    "request": {
        "url": "/employee",
        "method": "POST",
        "bodyPatterns": [
            {
                "equalToJson": "{\"name\":\"alex\",\"id\":\"001\",\"dept\":\"engineer\"}"
            }
        ]
    },
    "response": {
        "status": 201,
        "body": "Employee added."
    }
	}
	
	
	yet another example
	{
    "request": {
        "url": "/addemployee",
        "method": "POST",
        "bodyPatterns": [
            {
                "equalToJson": "{\"name\":\"alex\",\"id\":\"001\",\"dept\":\"engineer\"}"
            }
        ]
    },
    "response": {
        "status": 201,
        "body": "Employee added."
    }
}
	
5. Use the mock apis now.
   Test data

---------------------------------end -------------------------------------------