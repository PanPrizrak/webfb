package com.example.webfb.application.repository;

import com.example.webfb.application.entity.Worker;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface WorkerRepository extends CrudRepository<Worker, Long> {

    Iterable<Worker> findByName(String name);

}
