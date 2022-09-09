package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.AccountDTO;
import com.mypenbox.mpb.services.AccountService;
import com.mypenbox.mpb.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AccountController {

    private final RegistrationService registrationService;
    private final AccountService accountService;

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("account", accountDTO);
        return "registration/sign-up";
    }

    @PostMapping("/sign-up")
    public String submitSignUp(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                               BindingResult bindingResult, Model model) {

        try {
            if (bindingResult.hasErrors()) {
                return "registration/sign-up";
            } else {
                registrationService.register(accountDTO);
                return "registration/success";
                // TODO: send me again <- make that button work
            }
        } catch (DataIntegrityViolationException e) {
            String accountExists = accountService.accountExists(accountDTO);
            model.addAttribute("accountExists", accountExists);

            return "registration/sign-up";
        }

    }

    @GetMapping(path = "/sign-up/confirm")
    public String confirm(@Param("token") String token, Model model) {

        String confirmationResult = registrationService.confirmToken(token);
        model.addAttribute("confirmationResult", confirmationResult);
        return "registration/confirmation";
    }

    @GetMapping(path = "/resend")
    public String openResendPage(Model model) {
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("account", accountDTO);
        return "registration/resend";
        // TODO: UI + back-end validation for resend page
    }

    @PostMapping(path = "/resend-link")
    public String resend(@ModelAttribute("account") AccountDTO accountDTO,
                         BindingResult bindingResult, Model model) {

        String resendResult = registrationService.resendToken(accountDTO);
        model.addAttribute("resendResult", resendResult);

        return "registration/resend";
    }

    @GetMapping(path = "/login")
    public String returnLoginPage() {

        return "registration/login";
    }

    // TODO: UI + back-end validation for login page

    // TODO: /logout button
    @GetMapping(path = "/logout")
    public String returnMainPage() {
        return "registration/index";
    }
}
