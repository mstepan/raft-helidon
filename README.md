# raft-helidon service

Service that implements [Raft](https://en.wikipedia.org/wiki/Raft_(algorithm)) protocol using Helidon as framework.

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

