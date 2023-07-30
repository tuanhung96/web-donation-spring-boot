package com.example.asm1.controller;

import com.example.asm1.entity.Donation;
import com.example.asm1.entity.User;
import com.example.asm1.entity.UserDonation;
import com.example.asm1.service.DonationService;
import com.example.asm1.service.UserDonationService;
import com.example.asm1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private DonationService donationService;
    private UserDonationService userDonationService;
    private UserService userService;

    @Autowired
    public UserController(DonationService donationService, UserDonationService userDonationService, UserService userService) {
        this.donationService = donationService;
        this.userDonationService = userDonationService;
        this.userService = userService;
    }

    @GetMapping("/detailDonation")
    public String detail(@RequestParam("donationId") int donationId, Model theModel) {

        // get donation by id
        Donation donation = donationService.findById(donationId);
        theModel.addAttribute("donation", donation);

        // get user donate successfully
        List<UserDonation> successfullUserDonations = userDonationService.getSuccessfullUserDonations(donationId);
        theModel.addAttribute("successfullUserDonations", successfullUserDonations);

        return "user/detail";
    }

    @PostMapping("addUserDonation")
    public String addUserDonation(RedirectAttributes theModel, Principal principal,
                                  @RequestParam("name") String name,
                                  @RequestParam("money") int money,
                                  @RequestParam("message") String message,
                                  @RequestParam("donationId") int donationId) {

        // set status = 2 : chờ xác nhận
        int status = 2;
        String date = java.time.LocalDate.now().toString();

        // create donation for this userDonation
        Donation donation = donationService.findById(donationId);

        User user = userService.findByEmail(principal.getName());

        // create userDonation and save to database
        UserDonation userDonation = new UserDonation(name,money,date,message,status,donation,user);
        userDonationService.saveUserDonation(userDonation);

        // set attribute donateSuccess
        theModel.addFlashAttribute("donateSuccessfully", "DonateSuccessfully");

        return "redirect:/home";
    }
}
