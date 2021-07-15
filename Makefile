db:
	docker-compose down
	docker-compose up -d

run:
	./gradlew bootRun
