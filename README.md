# Event PubSub with Kafka

Following are major functionality of the project:
 * Get the top 50 Git Events from  https://api.github.com/events
 * Publish the data to kafka topic
 * Acquire the data from kafka and pass it ahead to apply filters  

Tools & technologies:
   * Core Java
   * Apache Kafka
   * Docker

List of Services:
 * IngestEventData
 * SubscribeEventData

### Steps for deploying The project
________
<p>
<b>Step 1</b>

Build the Docker image using dockerfile from the jar file 

```
docker build -t localhost:5000/git-event-pub-sub:1.0 -f Dockerfile .
``` 
</p>

<p>
<b>Step 2</b>

Push the docker image on local docker registry

```
docker push  localhost:5000/git-event-pub-sub:1.0
```
</p>

<p>
<b>Step 3</b>

Run the following command to run the application services 
 
``` 
docker-compose -f docker-compose.yml up -d
```
</p>




