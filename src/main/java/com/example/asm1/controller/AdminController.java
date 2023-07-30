package com.example.asm1.controller;

import com.example.asm1.entity.Donation;
import com.example.asm1.entity.Role;
import com.example.asm1.entity.User;
import com.example.asm1.entity.UserDonation;
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

    @GetMapping("/unlockUser")
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
    public String donation(Model theModel) {
        // get users from service
        List<Donation> donations = donationService.getDonations();

        // add users to the Model
        theModel.addAttribute("donations", donations);

        return "admin/donation";
    }

    @PostMapping("/addDonation")
    public String addDonation(RedirectAttributes theModel,
                              @RequestParam("code") String code,
                              @RequestParam("name") String name,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              @RequestParam("organizationName") String organizationName,
                              @RequestParam("phoneNumber") String phoneNumber,
                              @RequestParam("description") String description) {

        // money = 0 when create donation
        int money = 0;

        // status = 1 : Mới tạo
        int status = 1;

        // create donation and save to database
        Donation donation = new Donation(code, name, startDate, endDate, organizationName, phoneNumber, money, status, description);
        donationService.saveDonation(donation);

        // set attribute addDonationSuccess
        theModel.addFlashAttribute("addDonationSuccess", "Successfully");

        return "redirect:/admin/donation";
    }

    @PostMapping("/deleteDonation")
    public String deleteDonation(@RequestParam("donationId") int theId) {
        donationService.deleteById(theId);
        return "redirect:/admin/donation";
    }

    @PostMapping("/updateDonation")
    public String updateDonation(@RequestParam("donationId") int theId,
                                 @RequestParam("code") String code,
                                 @RequestParam("name") String name,
                                 @RequestParam("startDate") String startDate,
                                 @RequestParam("endDate") String endDate,
                                 @RequestParam("organizationName") String organizationName,
                                 @RequestParam("phoneNumber") String phoneNumber,
                                 @RequestParam("money") int money,
                                 @RequestParam("status") int status,
                                 @RequestParam("description") String description) {

        // create donation
        Donation donation = new Donation(code, name, startDate, endDate, organizationName, phoneNumber, money, status, description);
        donation.setId(theId);

        // update donation in database
        donationService.saveDonation(donation);

        return "redirect:/admin/donation";
    }

    @GetMapping("/detailDonation")
    public String adminDetail(@RequestParam("donationId") int donationId, Model theModel) {
        // get donation by id
        Donation donation = donationService.findById(donationId);
        theModel.addAttribute("donation", donation);

        // get all userDonations
        List<UserDonation> userDonations = userDonationService.getUserDonations(donationId);
        theModel.addAttribute("userDonations", userDonations);

        return "admin/detail";
    }

    @PostMapping("/statusToDonate")
    public String statusToDonate(@RequestParam("donationId") int theId) {
        // get donation by id
        Donation donation = donationService.findById(theId);

        // set status = 2 : Đang quyên góp
        donation.setStatus(2);

        // update donation in database
        donationService.saveDonation(donation);

        return "redirect:/admin/donation";
    }

    @PostMapping("/statusToFinish")
    public String statusToFinish(@RequestParam("donationId") int theId) {
        // get donation by id
        Donation donation = donationService.findById(theId);

        // set status = 3 : Kết thúc quyên góp
        donation.setStatus(3);

        // update donation in database
        donationService.saveDonation(donation);

        return "redirect:/admin/donation";
    }

    @PostMapping("/statusToClose")
    public String statusToClose(@RequestParam("donationId") int theId) {
        // get donation by id
        Donation donation = donationService.findById(theId);

        // set status = 4 : Đóng quyên góp
        donation.setStatus(4);

        // update donation in database
        donationService.saveDonation(donation);

        return "redirect:/admin/donation";
    }

    @PostMapping("/confirmUserDonation")
    public String confirmUserDonation(@RequestParam("userDonationId") int userDonationId,
                                      @RequestParam("donationId") int donationId) {

        // get userDonation by id
        UserDonation userDonation = userDonationService.findById(userDonationId);

        // set status = 1 : Đã xác nhận
        userDonation.setStatus(1);

        // update donation in database
        userDonationService.saveUserDonation(userDonation);

        // get donation by id
        Donation donation = donationService.findById(donationId);

        // caculate totalMoney and set money of this donation
        int totalMoney = donation.getMoney() + userDonation.getMoney();
        donation.setMoney(totalMoney);

        // update donation in database
        donationService.saveDonation(donation);

        return "redirect:/admin/detailDonation?donationId=" + donationId;
    }

    @PostMapping("/unconfirmUserDonation")
    public String unconfirmUserDonation(@RequestParam("userDonationId") int userDonationId,
                                        @RequestParam("donationId") int donationId) {

        // get userDonation by id
        UserDonation userDonation = userDonationService.findById(userDonationId);

        // set status = 3 : Hủy xác nhận
        userDonation.setStatus(3);

        // update donation in database
        userDonationService.saveUserDonation(userDonation);

        return "redirect:/admin/detailDonation?donationId=" + donationId;
    }
}
