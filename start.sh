#!/bin/sh


cd server/
mvn clean install
cd ..
docker-compose build
docker-compose up -d

cd server/
rm -R target/
cd ..

