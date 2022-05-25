# Onboarding Project - Vehicle Information Service

## Functional Description

The purpose of this API is to utilize the following technologies to create endpoints to retrieve information about vehicles.
It will utilize an external API for data and leverage technlogies like gRPC, WebFlux, Docker, Kubernetes (via GKE), and a new beta gRPC feature on Postman.


## Design Description

### 1.) Creating gRPC (streaming) endpoints using WebFlux

The proto file in the proto directory was used to create the gRPC server and service definitions.
It includes different types of client-server interactions that gRPC has to offer:
- Unary
- Client Streaming
- Server Streaming
- Bi-Directional Streaming

### 2.) Exposing gRPC server as REST API

The Controller file will show how the gRPC endpoints are exposed as a REST API along with a VehicleClient implementation that shows what each endpoint will execute.
Focusing on GET requests to showcase and focus on the streaming aspects of gRPC.

This REST API will leverage an external, free, API from the National Highway Traffic Safety Administration (NHTSA) containing all the data that's returned
(https://vpic.nhtsa.dot.gov/api).
  - external API request is handled using WebClient that's in the Spring Reactive library

## REST API DESCRIPTION

GET: /vehicle/allMakes
  - a unary endpoint (with no required input) that will return every car make found

GET: /vehicle/allMakesStream?batchSize={desired batch size for stream}
  - a server streaming endpoing which takes in a batchsize and will return a text event stream with every car make found but in batches of the given size

GET: /vehicle/models?makes={list of makes}
  - a client streaming endpoint where it will consume a list of car makes and return a single list of car models for each car make

GET: /vehicle/model?makes={list of makes}
  - a bi-directional endpoint where it will consume a list of car makes and return a text event stream of models for each car make in the given list


The JSON responses are parsed using org.json.JSONObject.

### 3.) Testing

Testing was done locally by utilizing the ServerBuilder in the default gRPC package.
Ran a gRPC server locally and used the new beta feature on Postman to invoke gRPC method calls, as it is able to even run streaming endpoints.


### 4.) Deployment

  - a Docker image was made and pushed to the Docker image repository
  - there was an initial plan to integrate Promtheus & Grafana to show metrics but scraped due to the fact that by adding Prometheus to the project disables HTTP/2, which is the crux of gRPC technology
  - on GKE, pushed the docker image to the Artifact Registry
  - created a test cluster on GCP
  - used CloudShell to create a deployment, and a service to externally expose the API created
  - the deployment can be reached via external IP for the deployment via Postman
  - `deployment.yaml` in the directory can be applied via kubectl CLI command to create deployment/service




