package com.tobeto.lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "returns")
public class Return {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "returnDate")
    private LocalDate returnDate;

    @Column(name = "lateFee")
    private double lateFee;

    @ManyToOne
    @JoinColumn(name = "borrowingId")
    private Borrowing borrowing;
}
