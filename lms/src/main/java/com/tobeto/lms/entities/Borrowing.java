package com.tobeto.lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrowings")
public class Borrowing {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "borrowingDate")
    private LocalDate borrowingDate;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Column(name = "dailyLateFee")
    private double dailyLateFee;

    @OneToMany(mappedBy = "borrowing")
    private List<Return> returns;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    
}
