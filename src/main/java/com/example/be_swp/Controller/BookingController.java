package com.example.be_swp.Controller;

import com.example.be_swp.Models.BookingHistory;
import com.example.be_swp.Repository.BookingRepository;
import com.example.be_swp.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")

public class BookingController {
    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("{bookingHistoryID}")
    public BookingHistory getBookingHistory(@PathVariable("bookingHistoryID") int bookingHistoryID){
        return bookingService.getBookingHistory(bookingHistoryID);
    }

    @GetMapping("/getAll")
    public List<BookingHistory> getAllBookingService(){
        return bookingService.getAllBookingHistory();
    }

    @PostMapping
    public String createBookingService(@RequestBody BookingHistory bookingHistory) {
        bookingService.createBookingHistory(bookingHistory);
        return "Booking created";
    }

    @PutMapping
    public String updateBookingService(@RequestBody BookingHistory bookingHistory) {
        bookingService.updateBookingHistory(bookingHistory);
        return "Booking updated";
    }

    @DeleteMapping("{bookingHistoryID}")
    public String deleteBookingService(@PathVariable("bookingHistoryID") int bookingHistoryID) {
        bookingService.deleteBookingHistory(bookingHistoryID);
        return "Booking deleted";
    }
}
