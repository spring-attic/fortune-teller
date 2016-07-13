#!/bin/bash

# delete apps
cf delete fortune-service -f
cf delete fortune-ui -f

# delete services
cf delete-service fortunes-db -f
cf delete-service config-server -f
cf delete-service service-registry -f
cf delete-service circuit-breaker-dashboard -f
