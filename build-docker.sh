#!/usr/bin/env bash
##############################################################################################
# Build docker image
##############################################################################################

echo "Rebuilding docker image"
docker build -t mylocal/raft-helidon:$(git rev-parse --short HEAD) .



