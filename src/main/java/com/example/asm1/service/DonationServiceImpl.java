package com.example.asm1.service;

import com.example.asm1.dao.DonationRepository;
import com.example.asm1.entity.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        donationRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public Donation findById(int id) {
        Optional<Donation> result = donationRepository.findById(id);
        Donation donation = null;

        if (result.isPresent()) {
            donation = result.get();
        } else {
            throw new RuntimeException("Did not find donation id - " + id);
        }
        return donation;
    }

    @Override
    @Transactional
    public Page<Donation> getDonationsAndPageable(PageRequest pageable) {
        return donationRepository.findAll(pageable);
    }

}
