#!/usr/bin/env sh

# Create the base image
sudo docker build -f Dockerfile.base -t alpine-java:base .
