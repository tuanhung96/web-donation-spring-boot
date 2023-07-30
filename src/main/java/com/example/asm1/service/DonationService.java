package com.example.asm1.service;

import com.example.asm1.entity.Donation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DonationService {
    List<Donation> getDonations();

    void saveDonation(Donation donation);

    void deleteById(int theId);

    Donation findById(int donationId);

    Page<Donation> getDonationsAndPageable(PageRequest pageable);
}
