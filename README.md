# Spring simple Web

A simple web application written using the Spring framework. Hibernate is used to work with the database. Working with the Postgresql database and basic functions **Create + Read + Update + Delete (CRUD).**
Also implemented the simplest visual effect using html pages.

### Task

Implement an interface with methods:

- void deleteUserById(int id)

- void save(User user)

- void updateUserById(User user)

- User findUserById(int id)

- List<User> findAllUsers()

### Tech

* Spring Boot;
* Postgresql;
* Hibernate;
* Html/Css.

### Result

**1.** First url request localhost:8080 returns the greeting page. User creation is done using the keyword **save()** on a separate page.

```java
    @GetMapping("/addUser")
    public String createUserPage() {
        return "createUserPage";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userRepo.save(user);
        return "redirect:/users";
    }
```

Then the user goes to the main page "users", where a list of all created users stored in the database.This request uses the keyword **findAllByOrderByIdAsc().**

```java
    @GetMapping("/users")
    public String users(Map<String, Object> model) {
        Iterable<User> findAllUsers = userRepo.findAllByOrderByIdAsc();
        model.put("users", findAllUsers);
        return "users";
    }
```

Result create and read functions:

![gif](https://github.com/bbogdasha/springSimpleWeb/blob/master/gif/createRead.gif)

---

**2.** Each created user has delete and update buttons. Function delete realised by keyword request **deleteById(id).**

```java
    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }
```

When updating the user, the admin redirects to a new page "update user", where you can change the parameters each users. This request uses the keyword **save().**

```java
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
```

Also additional function of filtering by all users in the database by names has also been implemented.
Result delete, update and filter functional:

![gif](https://github.com/bbogdasha/springSimpleWeb/blob/master/gif/deleteUpdateFiltr.gif)
