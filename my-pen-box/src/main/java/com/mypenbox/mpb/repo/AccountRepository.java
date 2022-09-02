package com.mypenbox.mpb.repo;

import com.mypenbox.mpb.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.email = ?1")
    Account findByEmail(String email);
    Account findByNickname(String nickname);
}
