package com.sprint.sb06deokhugamteam01.repository;

import com.sprint.sb06deokhugamteam01.domain.Book;
import com.sprint.sb06deokhugamteam01.domain.Review;
import com.sprint.sb06deokhugamteam01.domain.ReviewLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewLog, String> {

    @Query("SELECT r FROM Review r WHERE r.book.id = :bookId")
    List<Review> findByBookId(UUID bookId);

    @Modifying
    @Query("DELETE FROM Review r WHERE r.book.id = :bookId")
    void deleteByBookId(UUID bookId);

}
