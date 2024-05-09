package com.tobeto.lms.repositories;

import com.tobeto.lms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(int Id);

    //Optional<User> findByPenaltyAmount(double penaltyAmount);
}
