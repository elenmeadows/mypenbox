package com.mypenbox.mpb.services;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.AccountDTO;
import com.mypenbox.mpb.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository repo;


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

    public boolean emailExists(String email){
        return repo.findByEmail(email) !=null;
    }

    public boolean nicknameExists(String nickname){
        return repo.findByNickname(nickname) !=null;
    }
}
