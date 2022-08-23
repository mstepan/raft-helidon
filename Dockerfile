
# 1st stage, build the app
FROM maven:3.6-jdk-11 as build

WORKDIR /helidon

# Create a first layer to cache the "Maven World" in the local repository.
# Incremental docker builds will always resume after that, unless you update
# the pom
COPY pom.xml .
RUN mvn package -Dmaven.test.skip -Declipselink.weave.skip

# Do the Maven build!
# Incremental docker builds will resume here when you change sources
COPY src src
RUN mvn package -DskipTests
RUN echo "done!"

# 2nd stage, build the runtime image
#FROM openjdk:11-jre-slim
FROM openjdk:11-jdk-slim
WORKDIR /helidon

# Copy the binary built in the 1st stage
COPY --from=build /helidon/target/raft-helidon.jar ./
COPY --from=build /helidon/target/libs ./libs

#
# Use ENTRYPOINT with exec command, otherwise JAVA_OPTS won't be propagated properly to JVM process
# https://www.veracode.com/blog/secure-development/docker-and-javaopts
#
ENTRYPOINT exec java $JAVA_OPTS  -jar raft-helidon.jar

EXPOSE 7070
