package com.mypenbox.mpb.registration;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private AccountService accountService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String email = request.getParameter("email");

        String redirectURL = "/login_failure?error=invalid";

        if (exception.getMessage().contains("User is disabled")) {
            redirectURL = "/login_failure?error=disabled";
        } else if (exception.getMessage().contains("Bad credentials")) {
            Account account = accountService.findByEmail(email);
            if (account == null) {
                redirectURL = "/login_failure?error=not_found";
            }
        }

        super.setDefaultFailureUrl(redirectURL);
        super.onAuthenticationFailure(request, response, exception);
    }
}
