package com.example.be_swp.Service;

import com.example.be_swp.Models.BookingHistory;

import java.util.List;

public interface BookingServiceRepo {
    public BookingHistory getBookingHistory(int bookingHistoryID);
    public String createBookingHistory(BookingHistory bookingHistory);
    public String deleteBookingHistory(int bookingHistoryID);
    public String updateBookingHistory(BookingHistory bookingHistory);
    public List<BookingHistory> getAllBookingHistory();
}
