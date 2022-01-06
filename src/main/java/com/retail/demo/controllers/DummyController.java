package com.retail.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DummyController {

    @GetMapping("/")
    public String index(Model model) {
        String title = "Classic Cars Application";
        model.addAttribute("title", title);
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "<h1>This is the home page</h1>";
    }
}
