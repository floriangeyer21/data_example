package com.data.example.data_example.repositories;

import com.data.example.data_example.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
}
