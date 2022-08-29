package com.mypenbox.mpb.services;

import com.mypenbox.mpb.models.Account;
import com.mypenbox.mpb.models.AccountDTO;

public interface IAccountService {

    Account registerNewAccount(AccountDTO accountDTO);
}
