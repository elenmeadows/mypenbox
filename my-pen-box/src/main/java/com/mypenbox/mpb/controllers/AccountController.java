package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.AccountDTO;
import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.services.AccountService;
import com.mypenbox.mpb.services.ProductService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("account", accountDTO);
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String submitSignUp(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                               BindingResult bindingResult, Model model) {


        try {

            if (bindingResult.hasErrors()) {
                return "sign-up";
            } else {
                Account register = accountService.registerNewAccount(accountDTO);
                return "index";
            }

        } catch (DataIntegrityViolationException e) {

            List<Account> accountList = accountService.findAllAccounts();

            List<String> existingEmails = new ArrayList<>();
            for (int i = 0; i < accountList.stream().count(); i++) {
                existingEmails.add(accountList.get(i).getEmail());
            }

            List<String> existingNicknames = new ArrayList<>();
            for (int i = 0; i < accountList.stream().count(); i++) {
                existingNicknames.add(accountList.get(i).getNickname());
            }

            String emailCheck = accountDTO.getEmail();
            String nicknameCheck = accountDTO.getNickname();

            if(existingEmails.indexOf(emailCheck) != -1 && existingNicknames.indexOf(nicknameCheck) != -1) {
                String accountExists = "Sorry, that email & nickname have already been used";
                model.addAttribute("accountExists", accountExists);
            } else if(existingEmails.indexOf(emailCheck) != -1) {
                String accountExists = "Sorry, that email has already been used";
                model.addAttribute("accountExists", accountExists);
            } else if(existingNicknames.indexOf(nicknameCheck) != -1) {
                String accountExists = "Sorry, that nickname has already been used";
                model.addAttribute("accountExists", accountExists);
            }

            return "sign-up";
        }

    }
}
