package com.retail.demo.controllers;

import com.retail.demo.models.Office;
import com.retail.demo.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/list-all")
    public String getAllOffices(Model model) {
        List<Office> offices = this.officeService.getAllOffices();
        String title = "All offices";

        model.addAttribute("offices", offices);
        model.addAttribute("title", title);
        return "/office/office-list";
    }

    @GetMapping("/{id}")
    public String getOfficeById(Model model,@PathVariable Integer id) {
        Office office = null;
        try {
            office  = officeService.findById(id);
            model.addAttribute("allowDelete", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "No office found with that ID");
        }
        model.addAttribute("office", office);
        return "/office/office";
    }

    @GetMapping("/city")  ///////////
    public Office getAllOffices(@RequestParam String city) {
        return this.officeService.findByCity(city);
    }

    @GetMapping("/list-all/{country}")
    public String getAllOfficesByCountry(Model model,@PathVariable String country) {
        List<Office> offices = this.officeService.findByCountry(country);
        String title = "All offices in:" + country;  ///////////////////////

        model.addAttribute("title", title);
        model.addAttribute("offices", offices);

        return "/office/offices-list";
    }

    @GetMapping("/add-office")
    public String getAddOffice(Model model) {
        Office office = new Office();
        model.addAttribute("add", true);
        model.addAttribute("office", office);

        return "office/update";
    }

    @PostMapping("/add-office")
    public String addOffice(Model model,@ModelAttribute("office") Office office) {
        try {
            Office newOffice = officeService.save(office);
            return "redirect:/office/" + newOffice.getOfficeCode();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "/office/update";
        }
    }

    @GetMapping("/update-office/{id}")
    public String getUpdateOffice(Model model, @PathVariable Integer id) {
        Office office = null;
        try {
            office = officeService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("office", office);
        return "/office/update";
    }

    @PostMapping("/update-office/{id}")
    public String processUpdateOffice(Model model,
                               @PathVariable Integer id,
                               @ModelAttribute("office") Office office) {
        try {
            office.setOfficeCode(id);
            officeService.update(office);
            return "redirect:/office/" + office.getOfficeCode();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/office/update";
        }
    }

    @GetMapping("/delete-office/{id}")
    public String getDeleteOffice(Model model,
                                   @PathVariable Integer id) {
        Office office = null;
        try {
            office = officeService.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("office", office);
        return "/office/office";
    }

    @PostMapping("/delete-office/{id}")
    public String deleteOffice(Model model,
                                @PathVariable Integer id) {
        try {
            officeService.deleteById(id);
            return "redirect:/office/list-all";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/office/office";
        }
    }

   // @DeleteMapping("/delete-office/{id}")
   // public void deleteOffice(@PathVariable Integer id) {
   //     try {
   //         this.officeService.deleteById(id);
   //     } catch (Exception e) {
   //         e.printStackTrace();
   //     }
   // }
}
