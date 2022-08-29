package com.mypenbox.mpb.services;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.AccountDTO;
import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.repo.AccountRepository;
import com.mypenbox.mpb.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




@Service
@Transactional
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository repo;

    public List<Account> findAllAccounts() {
        return repo.findAll();
    }

    @Override
    public Account registerNewAccount(AccountDTO accountDTO) {

        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setNickname(accountDTO.getNickname());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(accountDTO.getPassword());
        account.setPassword(encodedPassword);

        return repo.save(account);
    }
}
