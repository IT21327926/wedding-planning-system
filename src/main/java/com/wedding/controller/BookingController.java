package com.wedding.controller;

import com.wedding.model.Booking;
import com.wedding.model.Vendor;
import com.wedding.service.BookingService;
import com.wedding.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private VendorService vendorService;

    // READ - show all bookings
    @GetMapping
    public String getAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings/list";
    }

    // CREATE - show form
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "bookings/add";
    }

    // CREATE - save booking
    @PostMapping("/save")
    public String saveBooking(@ModelAttribute Booking booking,
                              @RequestParam Long vendorId) {
        Vendor vendor = vendorService.getVendorById(vendorId).orElseThrow();
        booking.setVendor(vendor);
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}