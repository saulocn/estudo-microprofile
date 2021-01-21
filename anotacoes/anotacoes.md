## Criando o projeto
Criação de um projeto Quarkus com o Maven

```
mvn io.quarkus:quarkus-maven-plugin:1.6.0.Final:create
```

## Subindo o projeto com configurações diferentes

Para subir a aplicação em Quarkus:

```
mvn quarkus:dev
```

Para subir a aplicação no Open Liberty

```
mvn liberty:run -f pom-liberty.xml
```

## Config Sources

A menos que o `config_ordinal` do config source seja de prioridade máxima, existem meios de sobrescrever as propriedades mesmo após o empacotamento:


Por linha de comando:

```
java -Dquantidade.elementos.pagina=11 -jar target/microprofile-1.0-SNAPSHOT-runner.jar

```


Por variável de ambiente com o nome do parâmetro:

```
export QUANTIDADE_ELEMENTOS_PAGINA=2   

```