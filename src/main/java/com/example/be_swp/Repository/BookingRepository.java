package com.example.be_swp.Repository;

import com.example.be_swp.Models.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<BookingHistory, Integer> {

}
