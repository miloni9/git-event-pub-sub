# Event PubSub with Kafka

### Steps for depoying the architecture

<p>
<b>Step 1</b>

Build the Docker image using dockerfile from the jar file 

`docker build -t localhost:5000/git-event-pub-sub:1.0 -f Dockerfile .
` 
</p>

<p>
<b>Step 2</b>

Push the docker image on local docker registry

` docker push  localhost:5000/git-event-pub-sub:1.0
`
</p>

<p>
<b>Step 3</b>

Run the following command to run the application services 
 
` docker-compose -f docker-compose.yml up -d`
</p>