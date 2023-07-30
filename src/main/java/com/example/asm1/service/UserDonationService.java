package com.example.asm1.service;

import com.example.asm1.entity.UserDonation;

import java.util.List;

public interface UserDonationService {
    List<UserDonation> getUserDonations(int donationId);

    List<UserDonation> getSuccessfullUserDonations(int donationId);

    void saveUserDonation(UserDonation userDonation);

    UserDonation findById(int userDonationId);
}
