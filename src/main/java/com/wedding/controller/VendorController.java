package com.wedding.controller;

import com.wedding.model.Vendor;
import com.wedding.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // READ - show all vendors
    @GetMapping
    public String getAllVendors(Model model) {
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "vendors/list";
    }

    // CREATE - show form
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "vendors/add";
    }

    // CREATE - save vendor
    @PostMapping("/save")
    public String saveVendor(@ModelAttribute Vendor vendor) {
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
    public String showEditForm(@PathVariable Long id, Model model) {
        Vendor vendor = vendorService.getVendorById(id).orElseThrow();
        model.addAttribute("vendor", vendor);
        return "vendors/edit";
    }

    // UPDATE - save changes
    @PostMapping("/update")
    public String updateVendor(@ModelAttribute Vendor vendor) {
        vendorService.updateVendor(vendor);
        return "redirect:/vendors";
    }
}