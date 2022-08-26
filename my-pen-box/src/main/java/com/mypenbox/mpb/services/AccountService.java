package com.mypenbox.mpb.services;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.repo.AccountRepository;
import com.mypenbox.mpb.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository repo;

    public List<Account> findAllAccounts() {
        return repo.findAll();
    }

    public void save(Account account) {
        repo.save(account);
    }
}
