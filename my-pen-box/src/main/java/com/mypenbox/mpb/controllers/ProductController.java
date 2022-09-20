package com.mypenbox.mpb.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/catalog")
    public String viewCatalog(Model model) {
        return viewPage(model, 1, "colormark", "asc", null);
    }

    @GetMapping("/catalog/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum,
                           @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir,
                           @Param("keyword") String keyword) {

        // Paged & sorted product's list
        Page<Product> pageProduct = productService.listAll(pageNum, sortField, sortDir, keyword);
        List<Product> listProducts = pageProduct.getContent();

        // Unpaged sorted product's list
        List<Product> modalListProduct = productService.modalFindAll(sortField, sortDir, keyword);

        // Mapping of sorted product's list for modal window
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("my-pen-box/src/main/resources/json/modalListProduct.json"), modalListProduct);
        } catch(IOException e) {
            e.printStackTrace();
        } // Mapping of sorted product's list for modal window

        if (modalListProduct.stream().count() == 0) {
            return "catalog/nothing.html";
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", pageProduct.getTotalPages());
        model.addAttribute("totalItems", pageProduct.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir = "asc");
        model.addAttribute("reverseSortDir", sortDir = "desc");
        model.addAttribute("whatSortDir", pageProduct.getSort().get().findFirst().get().getDirection().toString().toLowerCase());

        model.addAttribute("listProducts", listProducts);

        model.addAttribute("keyword", keyword);

        return "catalog/catalog";
    }

    @GetMapping("/modal")
    public String productCard(Model model,
                                     @Param("productId") Long productId) throws IOException {

        Product productCard = productService.getProductById(productId);
        model.addAttribute("colorname", productCard.getColorname());
        model.addAttribute("colorswatch", productCard.getColorswatch());
        model.addAttribute("brand", productCard.getBrand());
        model.addAttribute("type", productCard.getType());
        model.addAttribute("colormark", productCard.getColormark());

        return "fragments/modal :: product-info";
    }

    @GetMapping("/modal-prev")
    public String prevProductCard(Model model,
                              @Param("productId") Long productId) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> modalListProduct = objectMapper.readValue(new File("my-pen-box/src/main/resources/json/modalListProduct.json"), new TypeReference<List<Product>>(){});

        Product productCard = modalListProduct.stream()
                .filter(product -> productId.equals(product.getId()))
                .findAny()
                .orElse(null);

        int productIndex = modalListProduct.indexOf(productCard);
        if (productIndex != 0)
            productIndex -= 1;


        String id = modalListProduct.get(productIndex).getId().toString();
        String colorname = modalListProduct.get(productIndex).getColorname();
        String colorswatch = modalListProduct.get(productIndex).getColorswatch();
        String brand = modalListProduct.get(productIndex).getBrand();
        String type = modalListProduct.get(productIndex).getType();
        String colormark = modalListProduct.get(productIndex).getColormark();

        model.addAttribute("id", id);
        model.addAttribute("colorname", colorname);
        model.addAttribute("colorswatch", colorswatch);
        model.addAttribute("brand", brand);
        model.addAttribute("type", type);
        model.addAttribute("colormark", colormark);

        return "fragments/modal :: product-info";
    }

    @GetMapping("/modal-next")
    public String nextProductCard(Model model,
                                  @Param("productId") Long productId) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> modalListProduct = objectMapper.readValue(new File("my-pen-box/src/main/resources/json/modalListProduct.json"), new TypeReference<List<Product>>(){});

        Product productCard = modalListProduct.stream()
                .filter(product -> productId.equals(product.getId()))
                .findAny()
                .orElse(null);

        int productIndex = modalListProduct.indexOf(productCard);
        Long totalItems = modalListProduct.stream().count();
        if (productIndex != totalItems)
            productIndex += 1;


        String id = modalListProduct.get(productIndex).getId().toString();
        String colorname = modalListProduct.get(productIndex).getColorname();
        String colorswatch = modalListProduct.get(productIndex).getColorswatch();
        String brand = modalListProduct.get(productIndex).getBrand();
        String type = modalListProduct.get(productIndex).getType();
        String colormark = modalListProduct.get(productIndex).getColormark();

        model.addAttribute("id", id);
        model.addAttribute("colorname", colorname);
        model.addAttribute("colorswatch", colorswatch);
        model.addAttribute("brand", brand);
        model.addAttribute("type", type);
        model.addAttribute("colormark", colormark);

        return "fragments/modal :: product-info";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "catalog/addProduct";
    }

    @GetMapping("/editProduct/{productId}")
    public String editProduct(Model model,
                              @PathVariable(name = "productId") Long productId) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);

        return "catalog/editProduct";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/catalog/catalog";
    }
}
