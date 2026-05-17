package com.wedding.controller;

import com.wedding.model.User;
import com.wedding.model.Vendor;
import com.wedding.service.VendorService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // READ - show all vendors
    @GetMapping
    public String getAllVendors(Model model, HttpSession session) {
        model.addAttribute("vendors", vendorService.getAllVendors());
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "vendors/list";
    }

    // CREATE - show form
    @GetMapping("/new")
    public String showAddForm(Model model, HttpSession session) {
        model.addAttribute("vendor", new Vendor());
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "vendors/add";
    }

    // CREATE - save vendor
    @PostMapping("/save")
    public String saveVendor(@Valid @ModelAttribute Vendor vendor,
                             BindingResult result,
                             Model model,
                             HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
            return "vendors/add";
        }
        vendorService.saveVendor(vendor);
        return "redirect:/vendors";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return "redirect:/vendors";
    }

    // UPDATE - show form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Vendor vendor = vendorService.getVendorById(id).orElseThrow();
        model.addAttribute("vendor", vendor);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "vendors/edit";
    }

    // UPDATE - save changes
    @PostMapping("/update")
    public String updateVendor(@Valid @ModelAttribute Vendor vendor,
                               BindingResult result,
                               Model model,
                               HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
            return "vendors/edit";
        }
        vendorService.updateVendor(vendor);
        return "redirect:/vendors";
    }
}