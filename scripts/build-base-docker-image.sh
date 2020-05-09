#!/usr/bin/env sh

# Create the base image
docker build -f Dockerfile.base -t alpine-java:local .
