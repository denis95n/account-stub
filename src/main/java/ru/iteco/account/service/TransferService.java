package ru.iteco.account.service;

import ru.iteco.account.model.dto.*;

public interface TransferService {

    Boolean transferBetweenBankBooks(TransferDto transferDto);

    void transferBetweenBankbooksUsers(TransferDto transferDto);
}
