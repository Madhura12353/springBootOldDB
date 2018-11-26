package com.student.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.student.demo.model.Student;


@Repository
public class StudentRepository {
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	
	public StudentRepository() {
		
		createTable();
	}
	

	private void createTable() {
		
		Connection connection = getDBConnection();
		Statement stmt = null;
		try {
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			stmt.execute("CREATE TABLE STUDENT(id int primary key, firstname varchar(255),lastname varchar(255),email varchar)");
			
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void insertWithStatement(int id, String firstname, String lastname, String email ){
		Connection connection = getDBConnection();
		Statement stmt = null;
		try {
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			
			stmt.execute("INSERT INTO STUDENT(id, firstname,lastname,email) VALUES("+id+", '"+firstname+"','"+lastname+"','"+email+"')");
//			
			ResultSet rs = stmt.executeQuery("select * from STUDENT");
			System.out.println("H2 In-Memory Database inserted through Statement");
			while (rs.next()) {
				System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("firstname")+ " lastname " + rs.getString("lastname") + " email " + rs.getString("email"));
			}

			//stmt.execute("DROP TABLE PERSON");
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
	
	public Student selectWithStatement(int id ){
		Connection connection = getDBConnection();
		Statement stmt = null;
		try {
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			

			ResultSet rs = stmt.executeQuery("select * from STUDENT where id="+id);
			System.out.println("H2 In-Memory Database inserted through Statement");

			Student student = new Student();
			rs.next();
			student.setId(rs.getInt("id"));
			student.setFirstname(rs.getString("firstname"));
			student.setLastname(rs.getString("lastname"));
			student.setEmail(rs.getString("email"));
			stmt.close();
			connection.commit();
			return student;
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return null;
	}
	
	
	private Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}


}
