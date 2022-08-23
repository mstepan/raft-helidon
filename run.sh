#!/usr/bin/env bash

export REMOTE_DEBUGGER=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=4000
export JAVA_OPTS="-XX:+UseParallelGC -XX:MaxRAMPercentage=75
  -XX:MaxDirectMemorySize=10M -XX:MaxMetaspaceSize=126953K -XX:ReservedCodeCacheSize=240M -Xss1M -Xmx1452054K
  -Djava.util.concurrent.ForkJoinPool.common.parallelism=0"
#export TLS_DEBUG=-Djavax.net.debug=ssl:handshake:verbose

java ${REMOTE_DEBUGGER} ${TLS_DEBUG} ${JAVA_OPTS} -jar target/raft-helidon.jar
