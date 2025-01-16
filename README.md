# Contact Management Application

This is a simple Contact Management Application built with Spring Boot. It provides RESTful APIs to manage contacts, including creating, updating, retrieving, and deleting contacts.

---

## Project Structure
```
.gitattributes
.gitignore
.mvn/
    wrapper/
        maven-wrapper.properties
.vscode/
HELP.md
mvnw
mvnw.cmd
pom.xml
src/
    main/
        java/
            com/
                renan/
                    contact/
                        ContactApplication.java
                        controller/
                            ContactController.java
                        dtos/
                            ContactDTO.java
                        models/
                            Contact.java
                        repostiories/
                            ContactRepository.java
                        servicies/
                            ContactService.java
        resources/
            application.properties
            static/
            templates/
    test/
        java/
            com/
                renan/
                    contact/
                        ContactApplicationTests.java
target/
```

---

## Prerequisites
- Java 17 or higher
- Maven 3.6.3 or higher
- MySQL database

---

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/renansouza12/Contact-Management-API.git
cd contact-management
```

### Configure the Database
Update the `application.properties` file with your MySQL database configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost/db
spring.datasource.username=yourusername
spring.datasource.password=yourpassport
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Build the Project
Use the Maven wrapper to build the project:
```bash
./mvnw clean install
```

### Run the Application
Run the application using the Maven wrapper:
```bash
./mvnw spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

---

## API Endpoints

### Get Contacts
- `GET /contacts`: Retrieve all contacts.
- `GET /contacts?name={name}`: Retrieve a contact by name.
- `GET /contacts?email={email}`: Retrieve a contact by email.
- `GET /contacts?phoneNumber={phoneNumber}`: Retrieve a contact by phone number.
- `GET /contacts?emails=true`: Retrieve all emails.
- `GET /contacts?numbers=true`: Retrieve all phone numbers.
- `GET /contacts/count`: Retrieve the count of all contacts.

### Create Contact
- `POST /contacts`: Create a new contact.
  - **Request Body**: `ContactDTO`

### Update Contact
- `PUT /contacts/contact?name={name}`: Update a contact by name.
  - **Request Body**: `ContactDTO`
- `PUT /contacts/{id}`: Update a contact by ID.
  - **Request Body**: `ContactDTO`

### Delete Contact
- `DELETE /contacts/contact/{name}`: Delete a contact by name.
- `DELETE /contacts/{id}`: Delete a contact by ID.

---

## Running Tests
Run the tests using the Maven wrapper:
```bash
./mvnw test
```


---

## Acknowledgments
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)
