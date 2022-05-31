# Onboarding Project - Vehicle Information Service

## Functional Description

The purpose of this API is to utilize the following technologies to create endpoints to retrieve information about vehicles.
It will utilize an external API for data and leverage technlogies like gRPC, WebFlux, Docker, Kubernetes (via GKE), and a new beta gRPC feature on Postman.


## Design Description

## 1.) Creating gRPC (streaming) endpoints using WebFlux

The proto file in the proto directory was used to create the gRPC server and service definitions.
It includes different types of client-server interactions that gRPC has to offer:
- Unary
- Client Streaming
- Server Streaming
- Bi-Directional Streaming



## 2.) Exposing gRPC server as REST API

The Controller file will show how the gRPC endpoints are exposed as a REST API along with a VehicleClient implementation that shows what each endpoint will execute.
Focusing on GET requests to showcase and focus on the streaming aspects of gRPC.

This REST API will leverage an external, free, API from the National Highway Traffic Safety Administration (NHTSA), and an online data set containing fuel-consumption reports:
  - `https://vpic.nhtsa.dot.gov/api` - external API request is handled using WebClient that's in the Spring Reactive library
  - `https://www.fueleconomy.gov/feg/download.shtml` - data is extracted and read into the repository, containing vehicle description and fuel-consumption data information

### REST API DESCRIPTION

GET: /vehicle
  - a unary endpoint that returns all the data that's been read into the repository (from 2019-2022)

GET: /vehicle/{id}
  - a unary endpoint that returns a specific vehicle detail by id

GET: /vehicle/details/{year}
  - a unary endpoint that returns vehicle details for a given year but currently only has years 2019-2022 (inclusive)

GET: /vehicle/allMakes
  - a unary endpoint (with no required input) that will return every car make found

GET: /vehicle/allMakesStream?batchSize={desired batch size for stream}
  - a server streaming endpoing which takes in a batchsize and will return a text event stream with every car make found but in batches of the given size

GET: /vehicle/models?makes={list of makes}
  - a client streaming endpoint where it will consume a list of car makes and return a single list of car models for each car make

GET: /vehicle/model?makes={list of makes}
  - a bi-directional endpoint where it will consume a list of car makes and return a text event stream of models for each car make in the given list


The JSON responses are parsed using org.json.JSONObject.


## 3.) Web-Embedded Mongodb Implementation

When the application is started, it will read the csv files in the `/resources/fueldata/` directory, extract the data that we want to leverage, and store them in the web-embedded mongodb. The extracted information can be accessed via REST API endpoints mentioned above. 
The file-reading process is done by turning a read stream into a Flux and parsing through each line that way, and saving each element.


## 4.) Testing

Testing was done locally by utilizing the ServerBuilder in the default gRPC package.
Ran a gRPC server locally and used the new beta feature on Postman to invoke gRPC method calls, as it is able to even run streaming endpoints.

If you want to run on your server, it should be as simple as running a gradle build and running the `GrpcVehicleDemoApplication` Java file. The endpoints will be available on `localhost:8080`.

### gRPC test on Postman
If you want to test gRPC endpoints specifically via Postman's new beta feature, use `localhost:9090` because that's where the gRPC server will be running.

1.) Create a new gRPC request

2.) Load up the proto file from the project directory under `/src/main/proto/vehicle/` called `vehicle.proto`

3.) Postman will show you the gRPC methods available

4.) Invoke with proper request bodies


`AllMakesStream` (server streaming)
  - takes in: 
    { 
      "batchSize": {integer} 
    }
  - but I recommend batchSize of at least 100 to avoid waiting too long for the stream to finish
  
`ModelsForMakes` (client streaming)
  - takes in 
    {
      "make": {desired car make}
    }
  - you can stream multiple requests (i.e. sending "honda", "tesla", "ford", "audi", and then press `End Streaming` to see the model responses for the given makes

`GetModelByMake` (bi-directional streaming)
  - takes in
    {
      "make": {desired car make}
    }
  - it works the same way as the endpoint above but you'll get a response each time you send a request


## 5.) Deployment

  - a Docker image was made and pushed to the Docker image repository
  - there was an initial plan to integrate Promtheus & Grafana to show metrics but scraped due to the fact that by adding Prometheus to the project disables HTTP/2, which is the crux of gRPC technology
  - on GKE, pushed the docker image to the Artifact Registry
  - created a test cluster on GCP
  - used CloudShell to create a deployment, and a service to externally expose the API created
  - the deployment can be reached via external IP for the deployment via Postman
  - `deployment.yaml` in the directory can be applied via kubectl CLI command to create deployment/service




