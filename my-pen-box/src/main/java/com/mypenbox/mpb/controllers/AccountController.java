package com.mypenbox.mpb.controllers;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.AccountDTO;
import com.mypenbox.mpb.models.PasswordDTO;
import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.services.AccountService;
import com.mypenbox.mpb.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AccountController {

    private final RegistrationService registrationService;
    private final AccountService accountService;

    @GetMapping("/signup")
    public String signUpForm(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            AccountDTO accountDTO = new AccountDTO();
            model.addAttribute("account", accountDTO);
            return "registration/signup";
        }
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String submitSignUp(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                               BindingResult bindingResult, Model model) {

        try {
            if (bindingResult.hasErrors()) {
                return "registration/signup";
            } else {
                registrationService.register(accountDTO);
                return "registration/success";
            }
        } catch (DataIntegrityViolationException e) {
            String accountExists = accountService.accountExists(accountDTO);
            model.addAttribute("accountExists", accountExists);
            return "registration/signup";
        }
    }

    @GetMapping(path = "/signup/confirm")
    public String confirm(@RequestParam("token") String token, Model model) {

        String confirmationResult = registrationService.confirmToken(token);
        model.addAttribute("confirmationResult", confirmationResult);
        return "registration/confirmation";
    }

    @GetMapping(path = "/signup/resend")
    public String openResendPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "registration/resend";
        }

        return "redirect:/";
    }

    @PostMapping(path = "/signup/resend")
    public String resend(HttpServletRequest request, Model model) {

        String email = request.getParameter("email");

        String resendResult = registrationService.resendToken(email);
        model.addAttribute("resendResult", resendResult);

        return "registration/resend";
    }

    @GetMapping(path = "/login")
    public String returnLoginPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "registration/login";
        }

        return "redirect:/index";
    }

    @GetMapping(path = "/login_failure")
    public String getAuthError(@RequestParam(name = "error") String authError, Model model) {

        String errorMessage = accountService.login(authError);
        model.addAttribute("errorMessage", errorMessage);
        return "registration/login";
    }

    @GetMapping("/login/reset")
    public String getResetPasswordPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "registration/resetPassword";
        }

        return "redirect:/";
    }

    @PostMapping("/login/reset")
    public String resetPassword(HttpServletRequest request, Model model) {

        String email = request.getParameter("email");

        String resetResult = registrationService.resetPassword(email);
        model.addAttribute("resetResult", resetResult);
        return "registration/resetPassword";
    }

    @GetMapping(path = "/login/updatePassword")
    public String showChangePasswordPage(@RequestParam(name = "token", required = false) String token, Model model) {

        String result = registrationService.validatePasswordResetToken(token);

        switch (result) {
            case ("was already used"):
                String invalidToken = "this reset link was already used. get a new one";
                model.addAttribute("invalidToken", invalidToken);
                return "registration/resetPassword";
            case ("expired"):
                invalidToken = "this reset link has expired. get a new one";
                model.addAttribute("invalidToken", invalidToken);
                return "registration/resetPassword";
            default:
                PasswordDTO passwordDTO = new PasswordDTO();
                passwordDTO.setToken(token);
                model.addAttribute("passwordDTO", passwordDTO);
                model.addAttribute("token", token);
                return "registration/updatePassword";
        }
    }

    @PostMapping(path = "/login/updatePassword")
    public String updatePassword(@ModelAttribute("passwordDTO") @Valid PasswordDTO passwordDTO,
                                 BindingResult bindingResult, Model model) {

        String token = passwordDTO.getToken();
        model.addAttribute("token", token);

        if (!bindingResult.hasErrors()) {
            String newPassword = passwordDTO.getNewPassword();
            accountService.changePasswordByPasswordResetToken(token, newPassword);
            String updateResult = "password has been successfully updated";
            model.addAttribute("updateResult", updateResult);
        }
        return "registration/updatePassword";
    }

    @GetMapping(path = "/logout")
    public String returnMainPage() {
        return "index";
    }

    @GetMapping("/{nickname}/edit")
    public String editAccount(Model model,
                              @PathVariable(name = "nickname") String nickname) {
        Account account = accountService.getAccountByNickname(nickname);
        model.addAttribute("account", account);

        return "account/edit";
    }
}
