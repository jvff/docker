#!/bin/bash

docker login

for image in janitovff/*; do
	docker push $image
done

docker logout
