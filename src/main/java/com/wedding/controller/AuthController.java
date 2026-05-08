package com.wedding.controller;

import com.wedding.model.User;
import com.wedding.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {
        User user = userService.loginUser(username, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    // Show register page
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle register
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute User user,
                                 @RequestParam String confirmPassword,
                                 Model model) {
        User savedUser = userService.registerUser(user, confirmPassword);
        if (savedUser != null) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Email already exists or passwords do not match!");
            return "register";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}