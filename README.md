## Building a app whit robust security connecting to Database (MySQL in this case) and creating a JSON web token using Spring Boot, Spring Security, REST Service and JSON WebToken

This application contains an implementation of Auditing and an implementation of a user profile that requires Spring Security

## Please follow the next steps:

1. **Clone the application**

	```bash
	git clone https://github.com/mcorverasoft/spring-security-db-jwt-app.git
	cd spring-security-db-jwt-app
	```

2. **Create a MySQL database**

	```bash
	create database myapp
	```

3. **Change MySQL username and password as per your MySQL installation**

	+ open `src/main/resources/application.properties` file.

	+ change `spring.datasource.username` and `spring.datasource.password` properties as per your mysql installation


4. **Run the file SpringSecurityDbJWTAppAplicationTest as J Unit (4)**
	
	Run a test unit for create users or roles, please edit the test case
	
5. **Run the app**

	You can run the spring boot app by typing the following command -

	```bash
	mvn spring-boot:run
	```
	
	The server will start on port 8081 (please go the aplication.properties file for edit it).
	
6 **Run postman to get a JWT**
	
	Plase create a new Request, method POST for login
	url http://localhost:8081/app/login
	
	on Body Tab send a type raw JSON Application, by example:
	```bash
	{
	 "usernameOrEmail":"youremail@gmail.com",
	 "password": "yourpassword"
	}
	```
    The post call return a JSON object wiht the token type jsonwebtoken
    
 7 **Call other Rest Services **
 	as http://localhost/list-user (type GET)
 	sending a Header "Autorization" with the value Bearer<yourtoken>
 	The app will verify if your token is valid in order to acces to http://localhost/list-user 
 	if your token is valid, the call will be succesfull, else the app return a 403 Error
 	
 8 **If you want create a new user**
 	create a new Request Post on postman, with url http://localhost:8081/app/signup
 	on Body Tab send a type raw JSON Application, by example:
	```bash
	{
	 "email":"youremail@gmail.com",
	 "username":"yourusername",
	 "name": "yourname",
	 "password": "yourpassword"
	}
	```
	 The post call return a JSON object wiht the token type jsonwebtoken and previously will create a new user

9 **Repeat step 6 and 7 with the new credentials

Your App already has robust security
