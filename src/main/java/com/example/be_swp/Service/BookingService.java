package com.example.be_swp.Service;

import com.example.be_swp.Models.BookingHistory;
import com.example.be_swp.Repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements BookingServiceRepo{

    BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingHistory getBookingHistory(int bookingHistoryID) {
        return bookingRepository.findById(bookingHistoryID).get();
    }

    @Override
    public String createBookingHistory(BookingHistory bookingHistory) {
        bookingRepository.save(bookingHistory);
        return "Created booking history";
    }

    @Override
    public String deleteBookingHistory(int bookingHistoryID) {
        bookingRepository.deleteById(bookingHistoryID);
        return "Delete booking history";
    }

    @Override
    public String updateBookingHistory(BookingHistory bookingHistory) {
        bookingRepository.save(bookingHistory);
        return "Updated booking history";
    }

    @Override
    public List<BookingHistory> getAllBookingHistory() {
        return bookingRepository.findAll();
    }
}
