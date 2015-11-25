# Reset all apps and backing services
if [[ $(cf apps | grep "fortune-service") ]];
then 
	echo 'Deleting Fortune Teller app instances'
	echo Y | cf delete fortune-ui
	echo Y | cf delete fortune-service

	echo 'De-registering backing services'
	echo Y | cf delete-service config-service
	echo Y | cf delete-service service-registry
	echo Y | cf delete-service fortune-db


	echo 'Deleting backing services application instances'
	echo Y | cf delete circuit-breaker
	echo Y | cf delete config-server
	echo Y | cf delete service-registry

	echo 'Checking apps and services are now blank'
	cf apps
	cf services
else
	echo "Exiting: No running Fortune Teller instances found. Check that the 'cf apps' command lists 'fortune-service'"
fi