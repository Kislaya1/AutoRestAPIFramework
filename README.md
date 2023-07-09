# <span style="font-family: Calibri; font-size: 2.8em;"> Rest API Pro Framework </span>
## <span style="font-family: Calibri; font-size: 2.8em;"> Introduction </span>
Hi All, I had created this API Framework in order automate APIs like a Pro.

## <span style="font-family: Calibri; font-size: 2.8em;"> Application Under Test </span>
[Restful booker](https://restful-booker.herokuapp.com/apidoc/index.html) - An API playground created by Mark Winteringham for those wanting to learn more about API testing and tools.

## <span style="font-family: Calibri; font-size: 2.8em;"> Pre-Requisite </span>
1. JDK 11 - as language of choice for writing this test framework.
2. Maven 3.8.2 - for project dependency management.
3. Any of your favorite IDEs (I prefer IntelliJ but code is independent of any IDEs)


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


