# <span style="font-family: Calibri; font-size: 2.8em;"> Rest API Pro Framework </span>
## <span style="font-family: Calibri; font-size: 2.8em;"> Introduction </span>
Hi All, I had created this API Framework in order automate APIs like a Pro.

## <span style="font-family: Calibri; font-size: 2.8em;"> Application Under Test </span>
[Restful booker](https://restful-booker.herokuapp.com/apidoc/index.html) - An API playground created by Mark Winteringham for those wanting to learn more about API testing and tools.

## <span style="font-family: Calibri; font-size: 2.8em;"> Pre-Requisite </span>
1. JDK 11 - as language of choice for writing this test framework.
2. Maven 3.8.2 - for project dependency management.
3. Any of your favorite IDEs (I prefer IntelliJ but code is independent of any IDEs)

## <span style="font-family: Calibri; font-size: 2.8em;"> Core Features </span>
- [x] Automate APIs irrespective of any Application Under Tests. This feature is available under APIBuilder in Project directory. 
It uses [Factory Design Pattern](https://www.baeldung.com/java-factory-pattern) and [Builder Design Pattern](https://refactoring.guru/design-patterns/builder).
- [x] Allows users to write fluent assertions for asserting both status and response body, without any code duplication.
- [x] Creates APIs that are scope/role agnostic using [Factory Design Pattern](https://www.baeldung.com/java-factory-pattern). This allows user to use the same APIs and data to write tests for different user roles and scopes.
- [x] Uses [Singleton Design Pattern](https://www.baeldung.com/java-singleton) in order to reuse the same auth token in all the tests for same role/scope.
- [x] Uses [Extent Report](https://www.extentreports.com/) for reporting and logging purpose.
- [x] Uses [PODAM](http://mtedone.github.io/podam/) library to auto-fill Java POJOs with random fake data.
- [x] Uses Fixture Factory Library in order build and organize fake objects for tests using templates format [BookingTemplates]().
- [x] Uses [Owner Library](https://matteobaccan.github.io/owner/) in order to fetch properties file data using simple Annotation Based Approach.
User is not required to code for loading, converting and managing of properties files.
- [x] Uses [ArgumentsProvider]() of Junit 5 in order to Parameterize the Tests.
- [x] Schema Validation is also part of this framework.
- [x] Uses Parallel Execution for which configuration is available under junit-platform.properties.:


## <span style="font-family: Calibri; font-size: 2.8em;"> Challenges Faced </span>

use the below command to run the test

mvn clean test -DENVIRONMENT=<EnvironmentName> -DPROD_ADMIN_USERNAME=<AdminUsername>
-DPROD_ADMIN_PASSWORD=<AdminPassword>

eg :
mvn clean test -DENVIRONMENT=PROD -DPROD_ADMIN_USERNAME=admin -DPROD_ADMIN_PASSWORD=password123

Note :
Environment Names available - PROD, QA
Also please provide valid username and password to run this automation.
For this case username and password can be fetched from this link

- https://restful-booker.herokuapp.com/apidoc/index.html#api-Auth-CreateToken


