# crossover-media
System for publishers to publish their journals (in  PDF format) and users can subscribe to them.

web-portal - An AngularJS frontend application. Only started to develop it based on Timezones applicaation.

src - Spring Boot application. 


two roles - publisher and subscriber


home for publisher:
published journals list
new publication




publisher:
1. Register new journal
    1. Name and description
    2. image logo - OPT
    3. submit 
2. view my journals
    1. Upload new issues
    2. delete issues
    3. view all issues
3. delete journal with all issues - OPT
4. 

Normal user:





POST /journals
GET /journals
GET /journals/8
DELETE /journals/8

POST /journals/8/issues		- multipart file
GET /journals/8/issues
GET /journals/8/issues/5 		-read issue
DELETE /journals/8/issues/7

POST /journals/8/subscribers	- subscribe current user

DELETE /journals/8/subscribers	- unsubscribe current user





journal

curl -H "Content-Type:application/json" -d '{"name":"journal number one","description":"jiurn1"}'  -v http://localhost:8080/journals 
