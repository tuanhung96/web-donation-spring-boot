package com.example.asm1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicController {


    @RequestMapping("/")
    public String getHome() {
        return "redirect:/home";
    }

//    @GetMapping("/home")
//    public String home(Model theModel, HttpServletRequest request) {
//
//        // get parameter "page"
//        String page = request.getParameter("page");
//
//        // set pageNumber that is choosen and add attribute
//        int pageNumber;
//        if(page == null) {
//            pageNumber = 1;
//        } else {
//            pageNumber = Integer.parseInt(page);
//        }
//        theModel.addAttribute("pageNumber", pageNumber);
//
//        // caculate totalPage and set attribute
//        int totalDonation = donationService.getTotalDonation();
//        int totalPage = totalDonation%5==0 ? totalDonation/5 : totalDonation/5+1;
//        theModel.addAttribute("totalPage", totalPage);
//
//        // caculate start position to get Donation in database and get donations
//        int start = (pageNumber-1)*5;
//        List<Donation> donations = donationService.getDonationsByPage(start);
//        theModel.addAttribute("donations", donations);
//
//        // set attribute donateSuccess and check if donate successfully or not
//        theModel.addAttribute("donateSuccess", request.getParameter("donateSuccess"));
//
//        return "public/home";
//    }
}
