package com.example.ecommerce.repository;

import com.example.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
     List<User> findByNameLike(String description);
     User findByUsername(String email);
}
