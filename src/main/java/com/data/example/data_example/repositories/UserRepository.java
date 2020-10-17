package com.data.example.data_example.repositories;

import com.data.example.data_example.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    @Query("from User u "
            + "join fetch u.groups g "
            + "join fetch u.tweets t where u.id = :id")
    Optional<User> findById(@Param("id")Long id);
}
