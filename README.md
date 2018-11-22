README
======
[Requirements](Requirements.md)

Go to the directory where the maven and docker files application files are located and run the following commands: 
	
	mvn clean package
	docker-compose up

Once the application has started run: 
	
	docker inspect 

This finds the external IP address of the docker container, which should be specified along with the application's port, 8080.

Open a new terminal window and make a POST request to the /payload API endpoint with the IP address and port: 

	curl -H "Content-Type: application/json" -X POST -d '{"content":"racecar"}' http://172.18.0.3:8080/payload 

The following JSON response should be displayed:
	
	{"timestamp":"2018-11-22T12:03:52.226+0000",
		"payloadId":1,
		"content":"racecar",
		"palindromeLength":7
	} 

You can also follow up with a GET request to the /payload/{id} API endpoint to retrieve the stored payload: 

	curl http://172.18.0.3:8080/payload/1  


In order to execute the unit tests, go to the application's top directory and run:
	
	mvn test