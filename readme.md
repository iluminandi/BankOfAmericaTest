# building and running the app

## To build, use the following command
    mvn clean install

## To run on local machine, use the following command
    mvn clean install spring-boot:run

    curl --location --request POST 'http://localhost:8080/createShortUrl' \
    --header 'Content-Type: application/json' \
    --data-raw '{
     "longUrl" : "https://www.google.com/search?q=google&oq=go&aqs=chrome.0.69i59j0i433j69i57j0i433l2j0i131i433j0i433j69i60.778j0j4&sourceid=chrome&ie=UTF-8"
    }'

    // given the previous returned MTYyMDA2NjA1MzYwNQ
    curl --location --request GET 'http://localhost:8080/MTYyMDA2NjA1MzYwNQ'

## To deploy on amazon EC2 (base on AWS beanstalk), use the following commands
    //aws cp local to s3
    aws s3 cp target/BankOfAmericaTest-0.0.1-SNAPSHOT.war s3://{bucket-name}/BankOfAmericaTest-0.0.1-SNAPSHOT.war

    //apply cloudformation
    aws s3 cp DevOps/ec2.template s3://{bucket-name}/ec2.template
    aws cloudformation create-stack --stack-name BankOfAmericaTest --template-url https://s3.amazonaws.com/{bucket-name}/ec2.template

## Reasoning behind design
######  The service generates a shorter and unique alias of long url by getting base64 encoded current millisecons.
######  The collisions of short url (alias) are unlikely but not completely ruled out, retry should solve the issue
######  The generated base64 encoded alias must be associated with long url, thereby they must be saved together in the repo
######  The saved combination of short and long urls must be unique

##  what can be done with more time
###### real SQL or noSQL Repository needs to be introduced
###### numerous error cases must be implemented for production solution (cases documented in the code with TODOSs)
###### Integration, Acceptance as well as Spring Contract Tests should be introduced
###### Circuit Breaker, Retryability can be introduced with Sidecar (Istio)
###### Logging/Auditing (sidecar, splunk)
###### Integration with naming service (Eureka), with API gateway Zuul, Spring Admin