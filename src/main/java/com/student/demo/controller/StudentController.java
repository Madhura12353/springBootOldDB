package com.student.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.demo.model.Student;
import com.student.demo.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@GetMapping(value = "/")
	public String getIndex() {
		return "Hello from Spring boot!";
	}

	
	@PostMapping(value="/createstudent")
	public  int createStudent(@RequestBody Student std) {
		int id = studentService.createStudent(std);
		return id;
	}
	
	@GetMapping(value="/getstudent")
	public Student getStudent(@RequestParam int id) {
		Student s = studentService.getStudent(id);
		return s;
	}
//	
//	@DeleteMapping(value="/deletestudent")
//	public int  deleteStudent(@RequestParam int id) {
//		
//		int id1  = studentService.deleteStudent(id);
//		return id1;
//	}
//	
//	@PutMapping(value="/updatestudent")
//	public  Student updateStudent(@RequestParam int id, @RequestBody Student std) {
//		Student updatedstd = new Student();
//		updatedstd =  studentService.updateStudent(id, std);
//		return updatedstd;
//	}


}
