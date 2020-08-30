package com.bogdan.web;

import com.bogdan.domain.User;
import com.bogdan.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class FilterController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<User> users;
        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findByFirstname(filter);
        } else {
            users = userRepo.findAllByOrderByIdAsc();
        }
        model.put("users", users);
        return "users";
    }
}
