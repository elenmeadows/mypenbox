package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.services.AccountService;
import com.mypenbox.mpb.services.ProductService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String submitSignUp(@ModelAttribute("account") @Valid Account account,
                               BindingResult bindingResult, Model model) {

//        List<Account> accountList = accountService.findAllAccounts();
//        List<String> existingEmails = new ArrayList<>();
//        for (int i = 0; i < accountList.stream().count(); i++) {
//            existingEmails.add(accountList.get(i).getEmail());
//        }
//        System.out.println(existingEmails);
//
//        String emailCheck = account.getEmail();
//
//        if(existingEmails.indexOf(emailCheck) != -1) {
//            String emailExists = "That email has already been used";
//            model.addAttribute("emailExists", emailExists);
//        }

        try {
            if (bindingResult.hasErrors()) {
                return "sign-up";

            } else
                accountService.save(account);
            return "index"; // SHOULD BE CHANGED ON SUCCESS REGISTRATION PAGE

        } catch (DataIntegrityViolationException e) {
            String errorCause = e.getRootCause().toString();
            System.out.println("THAT'S ERROR: " + errorCause + " END OF ERROR");

            if (errorCause.indexOf("email") != -1) {
                String accountExists = "That email has already been used";
                model.addAttribute("accountExists", accountExists);
            } else if (errorCause.indexOf("nickname") != -1) {
                String accountExists = "That nickname has already been used";
                model.addAttribute("accountExists", accountExists);
            }

            return "sign-up";
        }
    }

//    @GetMapping("/sign-up/check")
//    public String existsCheck(Model model,
//                               @Param("emailCheck") String emailCheck,
//                              @ModelAttribute("account") @Valid Account account) {
//
//        List<Account> accountList = accountService.findAllAccounts();
//        List<String> existingEmails = new ArrayList<>();
//        for (int i = 0; i < accountList.stream().count(); i++) {
//            existingEmails.add(accountList.get(i).getEmail());
//        }
//        System.out.println(existingEmails + " HERE WE GO!");
//
//        if (existingEmails.indexOf(emailCheck) != -1) {
//            String emailExists = "That email has already been used";
//            System.out.println("DO SMTH! " + model + " OR WHAT?!");
//            return "sign-up";
//        } else {
//            System.out.println("DOESN't EXIST!");
//            return "sign-up";
//        }
//    }
}
