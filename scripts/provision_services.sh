#!/bin/bash

echo "Provisioning service registry, config server and database as backing services"

# Checking service not already registered
if ! [[ $(cf services | grep "service-registry") ]]; then	
	# Checking PWS is the target
	if [[ $(cf target | grep "https://api.run.pivotal.io") ]]; then
	
		CONFIG_SERVICE_URL=$(cf app config-server | grep "urls: " | cut -c7- )
		echo ""
		SERVICE_REGISTRY_URL=$(cf app service-registry| grep "urls: " | cut -c7- )

		echo "Registering config server application instance '$CONFIG_SERVICE_URL' as a backing service"
		echo "http://$CONFIG_SERVICE_URL/" | cf cups config-service -p "uri"
		
		echo "Registering service registry application instance '$SERVICE_REGISTRY_URL' as a backing service"
		echo "http://$SERVICE_REGISTRY_URL/" | cf cups service-registry -p "uri"

		echo "Provisioning PostgreSQL database"
		cf cs elephantsql turtle fortune-db

		echo "Listing backing services"
		cf services
	else
		echo "Exiting script as this is intended for provisioning on Pivotal Web Services with API 'https://api.run.pivotal.io'"
	fi

else
	echo  "Exiting script as 'service-registry' found already running"
fi