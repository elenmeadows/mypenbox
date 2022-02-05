package com.mypenbox.mpb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/catalogue")
    public String catalogue() {
        return "catalogue";
    }
}
