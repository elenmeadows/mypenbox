package com.mypenbox.mpb.repo;

import com.mypenbox.mpb.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    Optional<Account> findByNickname(String nickname);

    @Transactional
    @Modifying
    @Query("UPDATE Account a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

}
