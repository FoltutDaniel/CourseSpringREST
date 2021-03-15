package com.zeke.REST;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeke.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	
	@PostConstruct
	public void loadData() {
		

		//hardcode for demo
		this.theStudents = new ArrayList<>();

		theStudents.add(new Student("Anima","Mori"));
		theStudents.add(new Student("Anima2","Mori2"));
		theStudents.add(new Student("Anima3","Mori3"));
		theStudents.add(new Student("Anima4","Mori4"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudentns(){

		return theStudents;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		return theStudents.get(studentId);
	}
}
