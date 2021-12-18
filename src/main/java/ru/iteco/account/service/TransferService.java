package ru.iteco.account.service;

import ru.iteco.account.model.dto.*;

public interface TransferService {

    Boolean transferBetweenBankBooks(BankBookTransferDto bankBookTransferDto);

    void transferBetweenBankbooksUsers(UsersTransferDto usersTransferDto);
}
