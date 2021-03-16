package com.zeke.REST;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeke.entity.Student;
import com.zeke.exception.StudentErrorResponse;
import com.zeke.exception.StudentNotFoundException;

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
		if((studentId >= theStudents.size()) || (studentId<0)) {
			throw new StudentNotFoundException("Student id not found -" + studentId);
		}
		return theStudents.get(studentId);
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
		StudentErrorResponse errorResponse = new StudentErrorResponse();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	// add an exception handler to catch any exception(catch all)
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
		StudentErrorResponse errorResponse = new StudentErrorResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST );
	}
}
