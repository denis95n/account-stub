package ru.iteco.account.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.account.model.dto.BankBookTransferDto;
import ru.iteco.account.model.dto.UsersTransferDto;
import ru.iteco.account.service.TransferService;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/transfer/bank-books")
@Validated
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public void transactBetweenBankBooks (@Valid @RequestBody BankBookTransferDto bankBookTransferDto){
        transferService.transferBetweenBankBooks(bankBookTransferDto);
    }

    @PostMapping("/users")
    public void transactBetweenBankBooksUsers (@Valid @RequestBody UsersTransferDto usersTransferDto){
        transferService.transferBetweenBankbooksUsers(usersTransferDto);
    }
}
