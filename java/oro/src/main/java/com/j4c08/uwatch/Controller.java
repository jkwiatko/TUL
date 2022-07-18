package com.j4c08.uwatch;

import com.j4c08.uwatch.data.models.User;
import com.j4c08.uwatch.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    UserService userService;

    @RequestMapping("/addUsers")
    public String createUsers() {
        User user1 = new User("Jacob", "Jacob1@gmail.com", "password213");
        User user2 = new User("Jacob1", "Jacob2@gmail.com", "password213");
        User user3 = new User("Jacob2", "Jacob3@gmail.com", "password213");
        User user4 = new User("Jacob3", "Jacob4@gmail.com", "password213");
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        return "users added";
    }

    @RequestMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("users/{id}")
    public String getUser(@PathVariable Integer id) {
        return userService.getUser(id).getLogin();
    }

    @RequestMapping("/users/update/{id}/name/{name}")
    public String runUpdate(@PathVariable Integer id, @PathVariable String name) {
        User user = userService.getUser(id);
        user.setLogin(name);
        userService.updateUser(user);
        return "updated user with name:" + name;
    }

    @RequestMapping("users/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "user with id:" + id + " has been deleted";
    }

}
