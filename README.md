# revoverflow-backend-answer-service
Answer service for RevOverflow




To build a docker container:

`docker build -t revoverflow-backend-answer-service .`

Run consul before running your container:

`docker run --net=host  --name=consul -d consul`

`docker images`

To run your container:

`docker run --net=host --name=revoverflow-answer-service -d <image-id>`


