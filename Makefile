docker_up d-up: docker_stop
	echo "Start docker"
	docker-compose up -d --build

docker_stop:
	docker-compose stop

docker_down:
	docker-compose down

