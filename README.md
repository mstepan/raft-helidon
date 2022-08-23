# raft-helidon service

Service that implements [Raft](https://raft.github.io/) protocol using Helidon as framework.

Check also Youtube video [Designing for Understandability: The Raft Consensus Algorithm](https://www.youtube.com/watch?v=vYp4LYbnnW8&t=2071s)

jenv with java 11 installed
```
    jenv local 11
```

Maven wrapper with maven 3.6.3 installed
```
mvn -N wrapper:wrapper -Dmaven=3.6.3
```

## Build & run
* build and package with maven 
```
./mvnw clean package
```
* Run application locally
```
./run.sh
```
* Use curl commands from file `curl-tests/operations.txt` file.


## Deploy to k8s

To deploy to local k8s cluster, just execute
```
kubectl apply -f app.yaml
```

Don't forget to clean up k8s deployment & service
```
kubectl delete -f app.yaml
```

