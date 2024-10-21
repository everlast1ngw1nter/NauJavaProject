package ru.everlast1ngw1nter.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.everlast1ngw1nter.NauJava.database.UserRepository;
import ru.everlast1ngw1nter.NauJava.domain.UserService;
import ru.everlast1ngw1nter.NauJava.models.User;

@Controller
@RequestMapping("/custom/users")
public class UserController
{
    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String getUsersList(Model model)
    {
        var users = userRepository.findAll();
        model.addAttribute("users", users);
        return "usersTable";
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    @ResponseBody
    public User addRegistration(User user) {
        userService.addUser(user);
        return user;
    }
}