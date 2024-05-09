## Maybank Assignment

This Spring Boot application with Angular is solely for job application purpose.

## Tech stack
1. Spring Boot version 3.2.5
2. Java 17
3. Angular v17.3.8

## Requirements
1.       Prepare a batch job to consume the attached text file.

2.       Prepare RESTFUL API to (1)Retrieve & (2) Update (description) the records

          a.      Must have authentication
          
          b.      Must have pagination
          
          c.      Can search by customer ID or account number(s) or description
          
          d.      For Update API, must able to handle concurrent update.

3.       Prepare frond-end screen to call RESTFUL API to retrieve and update the records.

          a.       Using Angular.

## Assumptions

1. Batch file provided by user will always have the same structure as per dataSource.txt
   a. The headers will always be the first row of the .txt file.
   b. The columns will be separated by |

2. The job to read the dataSource.txt will only run during start up.
3. Applicant is allowed to use InMemory database to store the transactions from the batch file.
4. To display login and authentication feature (Basic Auth), applicant is allowed to hardcode the username and password.
5. Applicant is allowed to use InMemoryUserDetailsManager for the authentication feature.

## Starting the project

For backend, please run the following command in the terminal

```
./gradlew bootRun
```
For frontend, please cd to webapp folder in another terminal and run the following commands

```
npm install
ng serve
```
