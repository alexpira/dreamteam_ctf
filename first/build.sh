#!/bin/bash

cd $(dirname $0)

echo "building $(basename `realpath .`)..."

chmod 700 ./mvnw

./mvnw -U clean package $OPTS "$@"

