# clientservice
To launch application use java -jar target/clientservice-0.0.1-SNAPSHOT.jar
To shutdown send POST request on localhost:8080/actuator/shutdown.

#Currently without tests.

This is RESTlike web service. 
 * Sending POST request with json data on localhost:8080/post
 * {
	"requestId": 1, "lastName": "Newcomer", "firstName": "Name", 
	"identDoc": { "type": 70, "numberSeries": "123123-1212", "issueDate": "22.12.1980" },
	"organCode": 17, 
	"arrest": { "date": "23.01.2001", "number": "#12.300", "basis": "for purpose", "amount": 12344,"operation": 1 }
    }
 *  will yield
 * {
    "arestId": 1,
    "code": "SUCCESS",
    "message": ""
    }
 * If there is no such client it will be created.
 * Client: id=1, lastName=Newcomer, firstName=Name, birthDate=null, birthPlace=null, 
 * identDoc=IdentDoc: docType=0, numberSeries=123123 12 12, issueDate=1980-12-22, arrests=[]
