jaeger: remove-jaeger
	docker run -d --name jaeger-microprofile -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 -p 14250:14250 -p 9411:9411 jaegertracing/all-in-one:1.17
quarkus:
	./mvnw  quarkus:dev
liberty:
	./mvnw liberty:run -f pom-liberty.xml
clean:
	./mvnw clean
remove-jaeger:
	docker rm -f jaeger-microprofile || true
add-quarkus-metrics:
	 mvn quarkus:add-extension -Dextensions="smallrye-metrics"
