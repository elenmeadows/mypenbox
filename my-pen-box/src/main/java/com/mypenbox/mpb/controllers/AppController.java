package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/catalog")
    public String viewCatalog(Model model) {
        return viewPage(model, 1, "colormark", "asc", null);
    }

    @RequestMapping("/catalog/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum,
                           @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir,
                           @Param("keyword") String keyword) {

        Page<Product> pageProduct = productService.listAll(pageNum, sortField, sortDir, keyword);

        List<Product> listProducts = pageProduct.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", pageProduct.getTotalPages());
        model.addAttribute("totalItems", pageProduct.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir = "asc");
        model.addAttribute("reverseSortDir", sortDir = "desc");
        model.addAttribute("whatSortDir", pageProduct.getSort().get().findFirst().get().getDirection().toString().toLowerCase());

        model.addAttribute("listProducts", listProducts);

        model.addAttribute("keyword", keyword);

        return "catalog";
    }



}
