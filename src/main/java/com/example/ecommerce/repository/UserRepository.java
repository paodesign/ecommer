package com.example.ecommerce.repository;

import com.example.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
     List<User> findByNameLike(String description);
     Optional<User> findByEmail(String email);
}
