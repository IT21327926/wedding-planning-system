package com.wedding.controller;

import com.wedding.model.Booking;
import com.wedding.model.Vendor;
import com.wedding.service.BookingService;
import com.wedding.service.VendorService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping
    public String getAllBookings(Model model, HttpSession session) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "bookings/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model, HttpSession session) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("vendors", vendorService.getAllVendors());
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "bookings/add";
    }

    @PostMapping("/save")
    public String saveBooking(@ModelAttribute Booking booking,
                              @RequestParam Long vendorId) {
        Vendor vendor = vendorService.getVendorById(vendorId).orElseThrow();
        booking.setVendor(vendor);
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
    @GetMapping("/confirm/{id}")
    public String confirmBooking(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id).orElseThrow();
        booking.setStatus("Confirmed");
        bookingService.updateBooking(booking);
        return "redirect:/bookings";
    }
}