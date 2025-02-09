package com.example.be_swp.Repository;

import com.example.be_swp.Models.BookingHistory;
import com.example.be_swp.Service.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<BookingHistory, Integer> {

}
