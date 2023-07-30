package com.example.asm1.controller;

import com.example.asm1.entity.Donation;
import com.example.asm1.service.DonationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    private DonationService donationService;

    @Autowired
    public HomeController(DonationService donationService) {
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String getHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model theModel, HttpServletRequest request) {
        // get parameter "page"
        String pageNumber = request.getParameter("page");
        int currentPage;
        if(pageNumber == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(pageNumber);
        }

        Integer pageSize = 5;
        PageRequest pageable = PageRequest.of(currentPage-1, pageSize);
        Page<Donation> pages = donationService.getDonationsAndPageable(pageable);
        List<Donation> donations = pages.getContent();
        theModel.addAttribute("donations", donations);
        theModel.addAttribute("totalPage", pages.getTotalPages());
        theModel.addAttribute("currentPage", currentPage);

        return "public/home";
    }
}
