# Car Park
The project should emulate a real situation in a company. The company employees can apply for rental of a car. The rental is applied to a given date. Based on such application, the system will offer list of free cars and the system allows reservation of a car.

## REST

Start REST: 
$ cd  carPark-rest
$ mvn tomcat7:run

CURL commands:

List employees:
$ curl -i -X GET http://localhost:8080/rest/employees

Delete employee:
$ curl -i -X DELETE http://localhost:8080/rest/employees/{id}
(In sample data, only employee with id 6 can be deleted, because he has no rentals.)
