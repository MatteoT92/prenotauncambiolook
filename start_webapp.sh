#!/usr/bin/bash

./mvnw spring-boot:run &

cd /src/main/resources/frontend
npm install &
npm start &


