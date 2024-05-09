package com.tobeto.lms.repositories;

import com.tobeto.lms.entities.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<Borrowing,Integer> {
    Optional<Borrowing> findById(int id);

}
