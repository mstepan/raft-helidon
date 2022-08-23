#!/usr/bin/env bash
##############################################################################################
# Build docker image first
##############################################################################################

#./build-docker.sh

##############################################################################################
# Run docker
##############################################################################################

docker run --rm -p 7070:7070  \
  --memory=2g --memory-reservation=512m \
  --cpus="2" \
  --name raft-helidon \
  -e JAVA_OPTS="-XX:+UseParallelGC -XX:MaxRAMPercentage=75 -Djava.util.concurrent.ForkJoinPool.common.parallelism=0
      -XX:MaxDirectMemorySize=10M -XX:MaxMetaspaceSize=98632K -XX:ReservedCodeCacheSize=240M -Xss1M -Xmx1611447K" \
  mylocal/raft-helidon:$(git rev-parse --short HEAD)


