package com.app.comtroller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.modal.Student;
import com.app.repo.StudentRepo;


@CrossOrigin(origins = "http://localhost:8023")
@RestController
@RequestMapping("/api")
@ResponseBody
public class StudentController {

	@Autowired
	  StudentRepo studentRepo;

	
	//Dispaly All Student
	  @GetMapping("/select")
	  public ResponseEntity<List<Student>> getAllStudent(@RequestParam(required = false) String id) {
	    
		  try {
			    List<Student> student= new ArrayList<Student>();

			    if (id == null)
			    	studentRepo.findAll().forEach(student::add);
			    else
			    	studentRepo.findByNameContaining(id).forEach(student::add);

			    if (student.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    }

			    return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
			  } catch (Exception e) {
			    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }

	//Dispaly Student based on the Id
	  @GetMapping("/student/{id}")
	  public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
		  java.util.Optional<Student> studentData = studentRepo.findById(id);

		  if (studentData.isPresent()) {
		    return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }

	  //insert Student Data
	  @PostMapping("/create")
	  public ResponseEntity<Student> createStudent(@RequestBody Student student1) {
		  try {
			    Student student = studentRepo.save(new Student(student1.getId(), student1.getName(), student1.getFee()));
			    return new ResponseEntity<>(student, HttpStatus.CREATED);
			  } catch (Exception e) {
			    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }
	  
     //Update command
	  @PutMapping("/students/{id}")
	  public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
		  java.util.Optional<Student> studentData = studentRepo.findById(id);

		  if (studentData.isPresent()) {
		    Student _tutorial = studentData.get();
		    _tutorial.setId(student.getId());
		    _tutorial.setName(student.getName());
		    _tutorial.setFee(student.getFee());
		    return new ResponseEntity<>(studentRepo.save(_tutorial), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		
	  }

	  //delete by ID
	  @DeleteMapping("/students/{id}")
	  public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") String id) {
		  try {
			  studentRepo.deleteById(id);
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  } catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }

	  //delete all
	  @DeleteMapping("/students")
	  public ResponseEntity<HttpStatus> deleteAllStudents() {
		  try {
			  studentRepo.deleteAll();
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  } catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }

	  

}
