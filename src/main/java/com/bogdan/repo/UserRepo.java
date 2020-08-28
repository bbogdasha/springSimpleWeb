package com.bogdan.repo;

import com.bogdan.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User getUserById(int id);

    void deleteUserById(int id);
}
