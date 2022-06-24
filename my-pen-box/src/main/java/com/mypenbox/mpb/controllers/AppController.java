package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ProductService service;

    @RequestMapping("/catalog")
    public String viewCatalog(Model model) {
        return viewPage(model, 1);
    }

    @RequestMapping("/catalog/page/{pageNum}")
    public String viewPage(Model model,
                              @PathVariable(name = "pageNum") int pageNum) {

        Page<Product> page = service.listAll(pageNum);

        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);

        return "catalog";
    }



}
