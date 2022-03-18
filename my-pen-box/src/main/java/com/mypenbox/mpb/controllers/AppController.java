package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppController {

    @Autowired
    private final ProductService productService;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/catalog")
    public String listProducts(Model model) {
        List<Product> products = productService.listAll();
        model.addAttribute("products", products);
        return "catalog";
    }

    @GetMapping("/catalog/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-info";
    }

    @PostMapping("/catalog/create")
    public String createProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/catalog";
    }

    @PostMapping("/catalog/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/catalog";
    }
}
