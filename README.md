#Event PubSub with Kafka 

### Steps for depoying the architecture

<p>
**Step 1**

Build the Docker image using dockerfile from the jar file 

`docker build -t localhost:5000/git-event-pub-sub:1.0 -f Dockerfile .
` 
</p>

<p>
**Step 2**

Push the docker image on local docker registry

` docker push  localhost:5000/git-event-pub-sub:1.0
`
</p>

<p>
**Step 3**

Run the following command to run the application services 
 
` docker-compose -f docker-compose.yml up -d`
</p>