#!/usr/bin/env bash

#export REMOTE_DEBUGGER=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=4000
export JAVA_OPTS="-XX:+UseParallelGC -XX:MaxRAMPercentage=75
  -XX:MaxDirectMemorySize=10M -XX:MaxMetaspaceSize=126953K -XX:ReservedCodeCacheSize=240M -Xss1M -Xmx1452054K
  -Djava.util.concurrent.ForkJoinPool.common.parallelism=0"

export CLUSTER_NODES="localhost:7070,localhost:7060,localhost:7050"

java ${REMOTE_DEBUGGER} ${JAVA_OPTS} \
  -Dserver.port=7070 -Dserver.sockets.health.port=7071 -Dserver.sockets.metrics.port=7072 \
  -jar target/raft-helidon.jar

#java ${REMOTE_DEBUGGER} ${JAVA_OPTS} \
#  -Dserver.port=7060 -Dserver.sockets.health.port=7061 -Dserver.sockets.metrics.port=7062 \
#  -jar target/raft-helidon.jar &
#
#java ${REMOTE_DEBUGGER} ${JAVA_OPTS} \
#  -Dserver.port=7050 -Dserver.sockets.health.port=7051 -Dserver.sockets.metrics.port=7052 \
#  -jar target/raft-helidon.jar &
