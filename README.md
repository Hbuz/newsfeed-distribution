# newsfeed-distribution
Application that polls a news feed and stores the data in an SQL database. The data can then be retrieved through GraphQL.

## Getting Started


### Prerequisites
 - JDK 8
 - Maven
 
 
### Installing
* Clone the repository
```
git clone git@github.com:Hbuz/newsfeed-distribution.git
```

* Create the database 'newsfeed'
```
CREATE DATABASE newsfeed;
```

* Creating an executable JAR
```
Run maven configuration with install command from the IDE
```
or type this from the root of the project:
```
mvn install
```


## Configuring the application
The application can be configured by the `application.properties` file

### Current configration
```
interval=60000
source=http://feeds.nos.nl/nosjournaal
format=format=xml
```
Also for the database:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/newsfeed
```


## Run the application
```
Run the application from the IDE
```
or type this from the root of the project:
```
mvn spring-boot:run
```
or as a packaged jar application
```
java -jar target/myapplication-0.0.1-SNAPSHOT.jar
```


## Retrieving the data from the Database with GraphQL
Open Graphiql from the browser:
```
http://localhost:8080/graphiql
```
Launch the GraphQL query to retrieve the data:
```
{
  feed {
    title
    description
    pubdate
    image
  }
}
```


## Built With
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Used to create the app
* [GraphQL](https://graphql.org/) - Query language for the API 
