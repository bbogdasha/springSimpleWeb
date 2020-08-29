package com.bogdan.repo;

import com.bogdan.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {

    User getUserById(int id);

    void deleteById(int id);

    List<User> findAllByOrderByIdAsc();
}
