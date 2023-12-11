# Serenity and Cucumber project

This is a test project using Serenity BDD and Cucumber, the project is In Progress.

## How to run the tests
1. Add URL in testData.json:
Update the **testData.json** file to include the URL required for testing. 
Ensure that the JSON structure includes a key for the URL, and set its corresponding value to the desired test URL.

`{

"url": "https://example.com",

"otherTestData": "value"

// ... other test data

}`

2. Add External Environment Variables for Browser Selection:
When running the tests, set an external environment variable (-Ddriver={Chrome/Firefox}) to specify the browser for test execution. 
This can be done through your test execution command. For example:

`mvn clean verify -Ddriver=Chrome`

3. Add External Environment Variable for User Data:
Similarly, set an external environment variable (-Duser={}) to specify which user data to use for test execution.

`mvn clean verify -Duser=User1`