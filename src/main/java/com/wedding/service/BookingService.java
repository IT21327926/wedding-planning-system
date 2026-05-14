package com.wedding.service;

import com.wedding.model.Booking;
import com.wedding.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // CREATE
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // READ ALL
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // READ ONE
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // DELETE
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    // UPDATE
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}