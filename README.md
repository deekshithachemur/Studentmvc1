# StudentMVC - JDBC + MVC (Servlet/JSP)

## Structure
- Model      : com.example.model.Student
- DAO (JDBC) : com.example.dao.StudentDAO, com.example.util.DBConnection
- Controller : com.example.controller.StudentServlet
- View       : src/main/webapp/WEB-INF/views/*.jsp

## Requirements
JDK 17+, Maven, MySQL, Apache Tomcat 10.1+ (jakarta namespace)

## Steps
1. MySQL lo `database/schema.sql` run cheyyi.
2. `DBConnection.java` lo USER / PASSWORD change cheyyi.
3. `mvn clean package`  -> target/StudentMVC.war
4. WAR ni Tomcat `webapps/` folder lo pettu, start cheyyi.
5. Browser: http://localhost:8080/StudentMVC/
