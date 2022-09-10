package com.mypenbox.mpb.services;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.AccountDTO;
import com.mypenbox.mpb.registration.token.ConfirmationToken;
import com.mypenbox.mpb.registration.token.ConfirmationTokenService;
import com.mypenbox.mpb.repo.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String accountExists(AccountDTO accountDTO) {
        boolean emailExists = accountRepository.findByEmail(accountDTO.getEmail())
                .isPresent();
        boolean nicknameExists = accountRepository.findByNickname(accountDTO.getNickname())
                .isPresent();

        if (emailExists && nicknameExists) {
            return "That email & nickname have already been used";
        } else if (nicknameExists) {
            return "That nickname has already been used";
        } else if (emailExists) {
            return "That email has already been used";
        }

        return null;
    }

    public String signUpUser(Account account) {
        String encodedPassword = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);

        accountRepository.save(account);

        return createToken(account);
    }

    public String createToken(Account account) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                account
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public Account findByEmail(String email) {

        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        if (accountOptional.isEmpty()) {
            return null;
        } else {
            Account accountObject = accountOptional.get();
            return accountObject;
        }
    }

    public String login(String authError) {

        switch (authError) {
            case ("disabled"):
                return "your account is not activated";
            case ("not_found"):
                return "no user found";
        }

        return "incorrect password";
    }

    public int enableAppUser(String email) {
        return accountRepository.enableAppUser(email);
    }

}
