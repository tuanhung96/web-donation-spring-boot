package com.example.asm1.dao;

import com.example.asm1.entity.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDonationRepository extends JpaRepository<UserDonation, Integer> {
}
