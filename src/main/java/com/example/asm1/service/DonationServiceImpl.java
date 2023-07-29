package com.example.asm1.service;

import com.example.asm1.dao.DonationRepository;
import com.example.asm1.entity.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService{
    private DonationRepository donationRepository;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    @Transactional
    public List<Donation> getDonations() {
        return donationRepository.findAll();
    }
}
