#!/usr/bin/env zsh

echo "Opening Hystrix circuit breaker dashboard in browser to view Forture Teller application under simluated load"
CIRCUIT_BREAKER_URL=$(cf app circuit-breaker | grep "urls: " | cut -c7- )
FORTUNE_UI_URL=$(cf app fortune-ui | grep "urls: " | cut -c7- )

echo 
if [[ $CIRCUIT_BREAKER_URL && FORTUNE_UI_URL ]];
then

	echo "Request $FORTUNE_UI_URL to seed dashboard"
	open "http://$FORTUNE_UI_URL"
	echo "Open $FORTUNE_UI_URL service in Hystrix service dashboard at $CIRCUIT_BREAKER_URL"
	open "http://$CIRCUIT_BREAKER_URL"'/hystrix/monitor?stream=http%3A%2F%2F'"$FORTUNE_UI_URL"'%2Fhystrix.stream'
	echo "Wait 20 seconds for Hystrix dashboard to start streaming results from $FORTUNE_UI_URL"
	sleep 20
	echo "Hit Fortune Teller UI with 20 web requests to show load characteristics"
	ab -m GET -c 1 -n 20 http://$FORTUNE_UI_URL/
else
	echo "Script exiting: Expected command 'cf apps' to list apps 'circuit-breaker' and 'fortune-ui'"
fi