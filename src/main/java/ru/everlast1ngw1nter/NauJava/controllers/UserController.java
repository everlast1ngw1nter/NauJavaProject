package ru.everlast1ngw1nter.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.everlast1ngw1nter.NauJava.database.UserRepository;

@Controller
@RequestMapping("/custom/users")
public class UserController
{
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public String getUsersList(Model model)
    {
        var users = userRepository.findAll();
        model.addAttribute("users", users);
        return "usersTable";
    }
}