package com.app.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.app.modal.Student;

//@EnableMongoRepositories
public interface StudentRepo extends MongoRepository<Student, String> {

	List<Student> findByNameContaining(String id);
	 // List<Student> findByTitleContaining(String id);
	  //List<Student> findByPublished(String name);
	
{

   "deviceId":"123",
   "timestamp":"09-AUG-21 11:20:30.45 AM ",
   "telemetryKey":"7000",

   "telemetryValue":"2345"

}
}
