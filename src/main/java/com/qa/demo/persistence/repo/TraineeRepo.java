package com.qa.demo.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.demo.persistence.domain.Trainee;

public interface TraineeRepo extends JpaRepository<Trainee, Long> {

}
