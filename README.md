README
======

Open a terminal window and navigate to the directory where the maven and docker files are located. 

Run the following commands: 
	
	mvn clean package 
	
	docker-compose up

Executing 'mvn clean package' and 'mvn test' will both run the unit tests 

Once the application has started up, run the following command in a new terminal tab: 
	
	docker inspect 

This finds the external IP address of the docker container, which should be specified along with the application's port, designated to 8080.

Open a new terminal window and make a POST request to the /payload API endpoint with this IP address and port: 

	curl -H "Content-Type: application/json" -X POST -d '{"content":"racecar"}' http://172.18.0.3:8080/payload 

The following JSON response should be displayed:
	
	{"timestamp":"2018-11-22T12:03:52.226+0000",
		"payloadId":1,
		"content":"racecar",
		"palindromeLength":7
	} 

You can also follow up with a GET request to the /payload/{id} endpoint with the received ID to retrieve the stored payload: 

	curl http://172.18.0.3:8080/payload/1 

Visting the base url http://172.18.0.3:8080/ on a web browser will provide a link. Clicking on the link executes a GET request to the /payload endpoint, which displays all database entries.