# <span style="font-family: Calibri; font-size: 2.8em;"> Auto Rest API Framework </span>

## <span style="font-family: Calibri; font-size: 2.8em;"> Introduction </span>

Hi All, I created this API Framework in order to automate REST APIs.

## <span style="font-family: Calibri; font-size: 2.8em;"> Application Under Test </span>

[Restful booker](https://restful-booker.herokuapp.com/apidoc/index.html) - An API playground created by Mark
Winteringham for those wanting to learn more about API testing and tools.

## <span style="font-family: Calibri; font-size: 2.8em;"> Pre-Requisite </span>

1. JDK 11 - as the language of choice for writing this test framework.
2. Maven 3.8.2 - for project dependency management.
3. Any of your favorite IDEs (I prefer IntelliJ but code is independent of any IDEs)

## <span style="font-family: Calibri; font-size: 2.8em;"> Core Features </span>

- [x] Automate APIs irrespective of any Application Under Test. This feature is available under APIBuilder in the
  Project directory. It uses [Factory Design Pattern](https://www.baeldung.com/java-factory-pattern)
  and [Builder Design Pattern](https://refactoring.guru/design-patterns/builder).
- [x] Allows users to write fluent assertions for asserting both status and response body, without any code duplication.
- [x] Creates APIs that are scope/role agnostic
  using [Factory Design Pattern](https://www.baeldung.com/java-factory-pattern). This allows users to use the same APIs
  and data to write tests for different user roles and scopes.
- [x] Uses [Singleton Design Pattern](https://www.baeldung.com/java-singleton) in order to reuse the same auth token in
  all the tests for the same role/scope.
- [x] Uses [Junit5 Reporting](https://howtodoinjava.com/junit5/junit-html-report/).
- [x] Uses [PODAM](http://mtedone.github.io/podam/) library to auto-fill Java POJOs with random fake data.
- [x] Uses [Fixture Factory Library](https://github.com/six2six/fixture-factory) in order to build and organize fake objects for tests using template format. For
  further understanding check out the [BookingTemplates](https://github.com/Kislaya1/RestApiProFramework/blob/main/src/main/java/com/rest/api/pro/templates/BookingTemplates.java)
  file inside the project.
- [x] Uses [Owner Library](https://matteobaccan.github.io/owner/) in order to fetch properties file data using a simple
  Annotation Based Approach. The user is not required to code for loading, converting, and managing of properties files.
- [x] Uses [Junit 5](https://reflectoring.io/tutorial-junit5-parameterized-tests/) in order to Parameterize the Tests.
- [x] Schema Validation is also part of this framework.
- [x] Uses Parallel Execution for which configuration is available under junit-platform.properties.:

## <span style="font-family: Calibri; font-size: 2.8em;"> Challenges Faced </span>

- [x] Use a generic mechanism in order to automate any REST APIs which should be independent of any Applications Under Test.<br />
<b>Solution</b> : Used Builder and Factory Design Pattern in order to achieve it.
- [x] Creates APIs so that they are scope/role agnostic.<br />
<b>Solution</b> : Used Factory Design Pattern in order to achieve it.
- [x] It becomes very difficult to create and maintain POJOs.<br />
<b>Solution</b> : Used PODAM library for same, it autofills POJOs with random fake data.
- [x] There is a lot of boilerplate code involved when trying to fetch data with a property file in Java.<br />
<b>Solution</b> : Used Owner Library in order to fetch properties file data using a simple Annotation Based Approach.
- [x] Creating a fluent assertion as part of this framework was one of the biggest challenge.<br />
<b>Solution</b> : Used basic inheritance principle using [SELF_TYPE](https://blog.joda.org/2007/08/java-7-self-types_1953.html) in java.
- [x] How to provide Parameterize test with random data into it.<br />
<b>Solution</b> : Used Junit5 in combination with Fixture Factory library in order to create random parameterized data.

## <span style="font-family: Calibri; font-size: 2.8em;"> Tools Set </span>

1. [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) - as language of choice for writing this test framework.
2. [Junit5](https://junit.org/junit5/) - as a testing framework for the Java programming language.
3. [Maven 3.8.2+](https://maven.apache.org/) - for project dependency management.
4. [RestAssured](https://rest-assured.io/) - in order to test and validate REST services in Java.

## <span style="font-family: Calibri; font-size: 2.8em;"> Steps to Execute </span>

1. Use the below maven command in order to execute test cases
```
mvn clean test -DENVIRONMENT=<Environment_Name> -D<Environment_Name>_ADMIN_USERNAME=<username> -D<Environment_Name>_ADMIN_PASSWORD=<password>
```
Here :<br />
a) Environment_Name : It will be based on [env.properties]() file (currently it is either PROD / QA but user can add multiple as required)<br />
b) Username and Password : It can be fetched from [Testing Site](https://restful-booker.herokuapp.com/apidoc/index.html#api-Auth-CreateToken)<br />

For eg : 
```
mvn clean test -DENVIRONMENT=PROD -DPROD_ADMIN_USERNAME=admin -DPROD_ADMIN_PASSWORD=password123
```

2. Use the below command in order to generate Junit HTML report.

In order to generate report, you just need to add 'site' at the end of mvn clean test command as below : 

```
mvn clean test -DENVIRONMENT=<Environment_Name> -D<Environment_Name>_ADMIN_USERNAME=<username> -D<Environment_Name>_ADMIN_PASSWORD=<password> site
```

3. Report will be generated under below directory structure, you can open the report in any browser of your choice

```
target/site/index.html
```
