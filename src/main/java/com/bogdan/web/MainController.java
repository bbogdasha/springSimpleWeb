package com.bogdan.web;

import com.bogdan.domain.User;
import com.bogdan.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/users")
    public String users(Map<String, Object> model) {
        Iterable<User> findAllUsers = userRepo.findAllByOrderByIdAsc();
        model.put("users", findAllUsers);
        return "users";
    }

    @GetMapping("/addUser")
    public String createUserPage() {
        return "createUserPage";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userRepo.getUserById(id));
        return "userPage";
    }

    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userRepo.getUserById(id));
        return "updateUserPage";
    }

    @PostMapping("/updateUser")
    public String update(@ModelAttribute("user") User user) {
        userRepo.save(user);
        return "redirect:/user/" + user.getId();
    }
}