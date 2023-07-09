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


