package com.pension.authorization.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pension.authorization.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

	User findByUsername(String username);
}
