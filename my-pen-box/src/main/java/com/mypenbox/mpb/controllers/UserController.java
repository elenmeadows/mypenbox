package com.mypenbox.mpb.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/sign-up")
    public String signUp() {

        return "sign-up";
    }
}
