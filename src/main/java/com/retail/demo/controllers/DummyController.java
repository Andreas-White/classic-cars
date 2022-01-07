package com.retail.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DummyController {

    @GetMapping("/")
    public String index(Model model) {
        String title = "Classic Cars Application";
        model.addAttribute("title", title);
        return "index";
    }
}
