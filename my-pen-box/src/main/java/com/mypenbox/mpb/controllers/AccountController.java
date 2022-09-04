package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.AccountDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AccountController {

    private final RegistrationService registrationService;

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
                System.out.println("LALLALA");
                return "sign-up";
            } else {
                registrationService.register(accountDTO);
                return "index";
                // TODO: return "success-page" (please, check your mailbox)
            }
        } catch (DataIntegrityViolationException e) {
            System.out.println("PARAM-PAM-PAM");
            // TODO: email/nickname already exists + modelView about that on UI level
            // TODO: email & nickname already exist -> link to send me confirmation letter again

            return "sign-up";
        }

    }

    @GetMapping(path = "/sign-up/confirm")
    public String confirm(@Param("token") String token, Model model) {

        String confirmationResult = registrationService.confirmToken(token);

        model.addAttribute("confirmationResult", confirmationResult);
        return "login";
        // TODO: return "confirmation" (th:if="${confirmationResult = "..."} x3)
        // TODO: if confirmed -> link to LOGIN page
        // TODO: if email has already been confirmed -> just that info
        // TODO: if expired -> link to send me confirmation letter again
    }

    @GetMapping(path = "/login")
    public String returnLoginPage() {
        return "login";
    }

    // TODO: /logout page & button
}
