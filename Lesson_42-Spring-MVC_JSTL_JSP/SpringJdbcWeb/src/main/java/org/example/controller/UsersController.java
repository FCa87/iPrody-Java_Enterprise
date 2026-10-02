package org.example.controller;


import org.example.dao.UserDao;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "users";
    }

    @GetMapping("/add")
    public String getUserAddPage() {
        return "user-add";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") User user) {
        userDao.create(user);

        return "redirect:/users";
    }

    @GetMapping("/search-redirect")
    public String redirectPage(@RequestParam("id") Integer id) {
        return "redirect:/users/search/" + id;
    }

    @GetMapping("/search/{id}")
    public String searchUserById(@PathVariable("id") Integer id, Model model){
        User user = userDao.findById(id);
        if (user != null){
            model.addAttribute("foundUser", user);
        } else {
            model.addAttribute("errorMessage", "Пользователь с Id " + id + " не найден");
        }
        return "user-by-id";
    }

}
