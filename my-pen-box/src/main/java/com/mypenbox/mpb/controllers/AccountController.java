package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.AccountDTO;
import com.mypenbox.mpb.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                accountService.registerNewAccount(accountDTO);
                return "index";
            }

        } catch (DataIntegrityViolationException e) {

            boolean emailExists = accountService.emailExists(accountDTO.getEmail());
            boolean nicknameExists = accountService.nicknameExists(accountDTO.getNickname());

            if(emailExists && nicknameExists) {
                String accountExists = "Sorry, that email & nickname have already been used";
                model.addAttribute("accountExists", accountExists);
            } else if(emailExists) {
                String accountExists = "Sorry, that email has already been used";
                model.addAttribute("accountExists", accountExists);
            } else if(nicknameExists) {
                String accountExists = "Sorry, that nickname has already been used";
                model.addAttribute("accountExists", accountExists);
            }
//
            return "sign-up";
        }

    }

    @GetMapping("/log-in")
    public String loginForm() {
        return "log-in";
    }
}
