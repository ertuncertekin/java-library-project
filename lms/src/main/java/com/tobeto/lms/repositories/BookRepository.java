package com.tobeto.lms.repositories;

import com.tobeto.lms.entities.Book;
import com.tobeto.lms.services.dtos.responses.ListBookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findById(int id);

    @Query(value="Select new com.tobeto.lms.services.dtos.responses.ListBookResponse(p.id, p.title,p.author,p.publicationYear,p.bookStatus) " +
            "from Book p Where " +
            "LOWER(p.title) LIKE CONCAT('%', LOWER(?1), '%')" +
            " AND " +
            "LOWER(p.author) LIKE CONCAT('%', LOWER(?2), '%')"
            , nativeQuery = false)
        // Select * from products => Select p from Product p
    List<ListBookResponse> search(String query,String query2);

}
