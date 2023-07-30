package com.example.asm1.service;

import com.example.asm1.dao.UserDonationRepository;
import com.example.asm1.entity.User;
import com.example.asm1.entity.UserDonation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserDonationServiceImpl implements UserDonationService{
    private UserDonationRepository userDonationRepository;

    @Autowired
    public UserDonationServiceImpl(UserDonationRepository userDonationRepository) {
        this.userDonationRepository = userDonationRepository;
    }

    @Override
    @Transactional
    public List<UserDonation> getUserDonations(int donationId) {
        return userDonationRepository.findByDonationId(donationId);
    }

    @Override
    @Transactional
    public List<UserDonation> getSuccessfullUserDonations(int donationId) {
        return userDonationRepository.getSuccessfullUserDonations(donationId);
    }

    @Override
    @Transactional
    public void saveUserDonation(UserDonation userDonation) {
        userDonationRepository.save(userDonation);
    }

    @Override
    @Transactional
    public UserDonation findById(int userDonationId) {
        Optional<UserDonation> result = userDonationRepository.findById(userDonationId);
        UserDonation userDonation = null;

        if (result.isPresent()) {
            userDonation = result.get();
        } else {
            throw new RuntimeException("Did not find userDonation id - " + userDonationId);
        }
        return userDonation;
    }
}
