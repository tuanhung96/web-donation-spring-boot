package com.example.asm1.controller;

import com.example.asm1.entity.Donation;
import com.example.asm1.entity.Role;
import com.example.asm1.entity.User;
import com.example.asm1.service.DonationService;
import com.example.asm1.service.UserDonationService;
import com.example.asm1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private DonationService donationService;
    private UserDonationService userDonationService;

    @Autowired
    public AdminController(UserService userService, DonationService donationService, UserDonationService userDonationService) {
        this.userService = userService;
        this.donationService = donationService;
        this.userDonationService = userDonationService;
    }

    @GetMapping("/home")
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping("/account")
    public String account(Model model, HttpServletRequest request) {
        // get users from service
        List<User> users = userService.getUsers();

        // add users to the Model
        model.addAttribute("users", users);

        return "admin/account";
    }

    @PostMapping("/addUser")
    public String addUser(RedirectAttributes model,
                          @RequestParam("fullName") String fullName,
                          @RequestParam("email") String email,
                          @RequestParam("phoneNumber") String phoneNumber,
                          @RequestParam("address") String address,
                          @RequestParam("userName") String userName,
                          @RequestParam("password") String password,
                          @RequestParam("roleId") Integer roleId) {

        // check if email existed
        if (userService.userExists(email)) {
            model.addFlashAttribute("email_exists","email_exists");
            return "redirect:/admin/account";
        }

        // create role for this user
        Role role = new Role();
        role.setId(roleId);

        // set status = 1 : Hoạt động
        int status = 1;

        // create user and save to database
        User user = new User(fullName, email, phoneNumber, address, userName, password, status, role);
        userService.saveUser(user);

        // set attribute addUserSuccess
        model.addFlashAttribute("addUserSuccess", "Successfully");

        return "redirect:/admin/account";
    }

    @GetMapping("/lockUser")
    public String lockUser(@RequestParam("userId") int theId) {
        // get user by id
        User user = userService.findById(theId);

        // set status = 2 : Khóa
        user.setStatus(2);

        // update user in database
        userService.saveUser(user);

        return "redirect:/admin/account";
    }

    @PostMapping("/unlockUser")
    public String unlockUser(@RequestParam("userId") int theId) {
        // get user by id
        User user = userService.findById(theId);

        // set status = 2 : Hoạt động
        user.setStatus(1);

        // update user in database
        userService.saveUser(user);

        return "redirect:/admin/account";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/account";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("userId") int userId,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("email") String email,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("address") String address,
                             @RequestParam("userName") String userName,
                             @RequestParam("password") String password,
                             @RequestParam("status") int status,
                             @RequestParam("roleId") int roleId) {

        // create role for this user
        Role role = new Role();
        role.setId(roleId);

        // create user
        User user = new User(fullName, email, phoneNumber, address, userName, password, status, role);
        user.setId(userId);

        // update user in database
        userService.saveUser(user);

        return "redirect:/admin/account";
    }

    @GetMapping("/donation")
    public String donation(Model theModel, HttpServletRequest request) {
        // get users from service
        List<Donation> donations = donationService.getDonations();

        // add users to the Model
        theModel.addAttribute("donations", donations);

        // set attribute addDonationSuccess and check if add donation successfully or not
        theModel.addAttribute("addDonationSuccess", request.getParameter("addDonationSuccess"));

        return "admin/donation";
    }
}
