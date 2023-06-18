package com.nosqlexample.repo;

import com.nosqlexample.model.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CouchbaseRepository<User,String>{
    Optional<User> findByEmail(String email);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND ANY s IN users.sport SATISFIES s.sportName = $1 END")
    Optional<User> findBySport(String sport);
}