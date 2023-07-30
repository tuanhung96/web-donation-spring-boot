package com.example.asm1.dao;

import com.example.asm1.entity.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDonationRepository extends JpaRepository<UserDonation, Integer> {
    @Query("select u from UserDonation u where u.donation.id= ?1")
    List<UserDonation> findByDonationId(int donationId);

    @Query("select u from UserDonation u where u.donation.id= ?1 and u.status=1")
    List<UserDonation> getSuccessfullUserDonations(int donationId);
}
