Criação de um projeto Quarkus com o Maven

```
mvn io.quarkus:quarkus-maven-plugin:1.6.0.Final:create
```

Para subir a aplicação em Quarkus:

```
mvn quarkus:dev
```

Para subir a aplicação no Open Liberty

```
mvn liberty:run -f pom-liberty.xml
```
