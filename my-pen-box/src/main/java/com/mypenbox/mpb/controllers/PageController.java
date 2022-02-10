package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.Catalog;
import com.mypenbox.mpb.repo.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private CatalogRepository catalogRepository;

    @GetMapping("/catalog")
    public String catalog(Model model) {
        Iterable<Catalog> products = catalogRepository.findAll();
        model.addAttribute("product", products);
        return "catalog";
    }
}
