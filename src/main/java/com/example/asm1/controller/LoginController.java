package com.example.asm1.controller;

import com.example.asm1.entity.User;
import com.example.asm1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(Model theModel) {
        User user = (User) theModel.getAttribute("user");
        if (user != null) {
            theModel.addAttribute("user", user);
        } else {
            theModel.addAttribute("user", new User());
        }
        return "public/login";
    }

    @PostMapping("/register")
    public String register(RedirectAttributes theModel,
                           @ModelAttribute("user") User user,
                           @RequestParam("rePassword") String rePassword) {

        // check password
        if (!user.getPassword().equals(rePassword)) {
            theModel.addFlashAttribute("msg_register_error","msg_register_error");
            theModel.addFlashAttribute("user", user);
            return "redirect:/showLoginPage";
        }

        // check the database if user already exists
        if (userService.userExists(user.getEmail())) {
            theModel.addFlashAttribute("email_exists","email_exists");
            theModel.addFlashAttribute("user", user);
            return "redirect:/showLoginPage";
        }

        user.setStatus(1); // set status = 1 : Hoạt động
        userService.saveUser(user);

        return "public/register-success";
    }
}
