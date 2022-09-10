package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.AccountDTO;
import com.mypenbox.mpb.services.AccountService;
import com.mypenbox.mpb.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    }

    @PostMapping(path = "/resend-link")
    public String resend(@ModelAttribute("account") AccountDTO accountDTO, Model model) {

        String resendResult = registrationService.resendToken(accountDTO);
        model.addAttribute("resendResult", resendResult);

        return "registration/resend";
    }

    @GetMapping(path = "/login")
    public String returnLoginPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "registration/login";
        }

        return "redirect:/";
    }

    @GetMapping(path = "/login_failure")
    public String getAuthError(@RequestParam(name = "error") String authError, Model model) {

        String errorMessage = accountService.login(authError);
        model.addAttribute("errorMessage", errorMessage);
        return "registration/login";
    }

    // TODO: LOGIN PAGE = remember me option

    // TODO: /logout button for all pages

    @GetMapping(path = "/logout")
    public String returnMainPage() {
        return "index";
    }
}
