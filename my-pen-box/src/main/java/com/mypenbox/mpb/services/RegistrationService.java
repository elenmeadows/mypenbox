package com.mypenbox.mpb.services;

import com.mypenbox.mpb.models.AccountDTO;
import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.AccountAuthority;
import com.mypenbox.mpb.registration.email.EmailSender;
import com.mypenbox.mpb.registration.token.ConfirmationToken;
import com.mypenbox.mpb.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class RegistrationService implements IRegistrationService {

    private final AccountService accountService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public String register(AccountDTO accountDTO) {

        String token = accountService.signUpUser(new Account(
                accountDTO.getEmail(),
                accountDTO.getFirstName(),
                accountDTO.getLastName(),
                accountDTO.getNickname(),
                accountDTO.getPassword(),
                AccountAuthority.USER
        ));

        String link = "http://localhost:8080/sign-up/confirm?token=" + token;
        emailSender.send(accountDTO.getEmail(), buildEmail(accountDTO.getFirstName(), link));

        return null;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() !=null) {

            return "email already confirmed";
        }

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {

            return "expired";
        }

        confirmationTokenService.setConfirmedAt(token);
        accountService.enableAppUser(
                confirmationToken.getAccount().getEmail());

        return "confirmed";
    }

    private String buildEmail(String name, String link) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <link\n" +
                "        href=\"https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap\"\n" +
                "        rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body,\n" +
                "        div,\n" +
                "        h1,\n" +
                "        h2,\n" +
                "        header {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            border: 0;\n" +
                "            outline: 0;\n" +
                "            background: transparent;\n" +
                "        }\n" +
                "\n" +
                "        .main-container {\n" +
                "            margin: 1.5rem;\n" +
                "            border: 0.1rem solid #393636;\n" +
                "            border-radius: 0.7rem;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        header {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "            width: 100%;\n" +
                "            height: 3.2rem;\n" +
                "            border-bottom: 0.1rem solid #393636;\n" +
                "            /* background-color: #393636;\n" +
                "            border-radius: 0.7rem 0.7rem 0 0; */\n" +
                "        }\n" +
                "\n" +
                "        header h1 {\n" +
                "            font-family: 'Raleway', sans-serif;\n" +
                "            color: #393636;\n" +
                "            font-weight: 400;\n" +
                "            margin-bottom: 0.2rem;\n" +
                "        }\n" +
                "\n" +
                "        .mail-content {\n" +
                "            font-family: 'Montserrat', sans-serif;\n" +
                "            color: #393636;\n" +
                "            font-weight: 600;\n" +
                "            margin: 2.3rem 1rem 1.7rem 1rem;\n" +
                "        }\n" +
                "\n" +
                "        .mail-content h2 {\n" +
                "            font-family: 'Montserrat', sans-serif;\n" +
                "            color: #393636;\n" +
                "            font-weight: 800;\n" +
                "            font-size: 3rem;\n" +
                "            line-height: 1.4rem;\n" +
                "        }\n" +
                "\n" +
                "        .mail-content div {\n" +
                "            margin: 1.5rem 0;\n" +
                "        }\n" +
                "\n" +
                "        .mail-content a {\n" +
                "            font-family: var(--ff-montserrat);\n" +
                "            color: #393636;\n" +
                "            font-size: 0.9rem;\n" +
                "            font-weight: 500;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            text-transform: uppercase;\n" +
                "            padding: 0.5rem 3.4rem;\n" +
                "            border: 1px solid #393636;\n" +
                "            border-radius: 0.375rem;\n" +
                "        }\n" +
                "\n" +
                "        a:hover {\n" +
                "            background-color: #393636;\n" +
                "            color: #EFEEEE;\n" +
                "            border: 1px solid #393636;\n" +
                "            transition-duration: 0.4s;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"main-container\">\n" +
                "        <header>\n" +
                "            <h1>my pen-box</h1>\n" +
                "        </header>\n" +
                "        <div class=\"mail-content\">\n" +
                "            <h2>hi " + name + ",</h2>\n" +
                "            <p>thank you for registration!<br>\n" +
                "                please click on the link below to complete it.<br>\n" +
                "            </p>\n" +
                "            <div>\n" +
                "                <a class=\"link\" href=\"" + link + "\">\n" +
                "                    activate my account\n" +
                "                </a>\n" +
                "            </div>\n" +
                "\n" +
                "            <p>* link will expire in 15 minutes.<br>\n" +
                "                see you soon, xoxo</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}
