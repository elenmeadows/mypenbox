package com.mypenbox.mpb.repo;

import com.mypenbox.mpb.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public List<Account> findAll();
}
