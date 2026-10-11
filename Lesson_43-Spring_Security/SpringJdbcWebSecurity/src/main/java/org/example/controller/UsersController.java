package org.example.controller;


import org.example.dao.UserDao;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao){
        this.userDao = userDao;
    }

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
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.create(user);

        return "redirect:/users";
    }

    @GetMapping("/search-redirect")
    public String redirectPage(@RequestParam("id") Integer id, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(element -> element.getAuthority().compareToIgnoreCase("ADMIN") == 0)){
            return "redirect:/users/search/" + id;
        }
        return "redirect:/users/basic/" + id;
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

    @GetMapping("/basic/{id}")
    public String getBasicUserById(@PathVariable("id") Integer id, Model model){
        User user = userDao.getBasicUserById(id);
        if (user != null){
            model.addAttribute("foundUser", user);
        } else {
            model.addAttribute("errorMessage", "Пользователь с Id " + id + " не найден");
        }
        return "basic-user-by-id";
    }

}
