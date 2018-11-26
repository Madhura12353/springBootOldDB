package com.student.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.demo.model.Student;
import com.student.demo.repository.StudentRepository;


@Service
public class StudentService {
	
int id = 0;


@Autowired
StudentRepository studentdb;


public int createStudent(Student student) {
	id++;
	student.setId(id);

	studentdb.insertWithStatement(id, student.getFirstname(), student.getLastname(), student.getEmail());
	return id;

}

public Student getStudent(int id) {

	Student student = studentdb.selectWithStatement(id);
	return student;

}

}
