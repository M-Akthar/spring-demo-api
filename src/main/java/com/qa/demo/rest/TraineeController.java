package com.qa.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.persistence.domain.Trainee;
import com.qa.demo.persistence.repo.TraineeRepo;

@RestController
@RequestMapping("/trainee")
@CrossOrigin(origins = "http://localhost:3000")
public class TraineeController {

	private TraineeRepo repo;

	public TraineeController(TraineeRepo repo) {
		super();
		this.repo = repo;
	}
	
	@GetMapping("/getTrainee/{id}")
	public String getTrainee(@PathVariable("id") long id) {
		
		Optional<Trainee> student = repo.findById(id);
		
		if(student.isPresent()) {
			Trainee ref = student.get();
			return "We found " + ref.getName() + " from " + ref.getCity();
		} else {
			return "No account found";
		}
	}
	
	@GetMapping("/getAll")
	public List<Trainee> getAllTrainees(){
		List<Trainee> all = repo.findAll();
		return all;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteTrainee(@PathVariable("id") long id) {
		Optional<Trainee> student = repo.findById(id);
		
		if(student.isPresent()) {
			Trainee ref = student.get();
			repo.deleteById(id);
			return ref.getName() + " deleted";
		} else {
			return "No account found";
		}
	}
	
	@PostMapping("/create1/{name}/{city}")
	public String createRecord1(@PathVariable("name") String name, @PathVariable("city") String city) {
		Trainee ref = new Trainee();
		
		ref.setName(name);
		ref.setCity(city);
		
		repo.save(ref);
		
		return "Success";
	}
	
	@PostMapping("/create2")
	public String createRecord1(@RequestBody Trainee ref) {
		repo.save(ref);

		return "Success";
	}
	
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") long id, @RequestBody Trainee updatedTrainee) {
		
		Optional<Trainee> ref = repo.findById(id);
		
		if(ref.isPresent()) {
			Trainee current = ref.get();
			
			current.setName(updatedTrainee.getName());
			current.setCity(updatedTrainee.getCity());
			
			repo.save(current);
			
			return "Success";
		} else {
			return "Could not update";
		}
	}

}
