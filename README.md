# clientservice
To launch application use java -jar target/clientservice-0.0.1-SNAPSHOT.jar
To shutdown send empty POST request on localhost:8080/actuator/shutdown.

There are some unit tests.
It is needed integration testing.

This is RESTlike web service. 
Sending POST request with json data on localhost:8080/put like this:
```
{
	"requestId": 1, "firstName": "IVAN", "lastName": "ivanoV", 
	"identDoc": { "type": 21, "numberSeries": "12 12 123123", "issueDate": "22.12.1980" },
	"organCode": 39, 
	"arrest": { "docDate": "23.01.2001", "docNum": "#12 310", "purpose": "another", "amount": 345, "refDocNum": "12 12 123123", "operation": 1 }
}
```
yelds
```
{
    "arestId": 1,
    "code": 0,
    "message": ""
}
```
