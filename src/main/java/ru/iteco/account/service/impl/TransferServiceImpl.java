package ru.iteco.account.service.impl;

import org.springframework.stereotype.Service;
import ru.iteco.account.annotation.BankBookTransferTransaction;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.model.dto.BankBookTransferDto;
import ru.iteco.account.model.dto.UsersTransferDto;
import ru.iteco.account.model.exception.TransactionException;
import ru.iteco.account.service.BankBookService;
import ru.iteco.account.service.TransferService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final BankBookService bankBookService;
    @Resource
    private TransferService transferService;

    public TransferServiceImpl(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @Override
    @BankBookTransferTransaction
    public Boolean transferBetweenBankBooks(BankBookTransferDto bankBookTransferDto) {

        BankBookDto sourceBankBookDto = bankBookService.findById(bankBookTransferDto.getBankBookSourceId());
        BankBookDto targetBankBookDto = bankBookService.findById(bankBookTransferDto.getBankBookTargetId());

        sourceBankBookDto.setAmount(sourceBankBookDto.getAmount().subtract(bankBookTransferDto.getAmount()));
        targetBankBookDto.setAmount(targetBankBookDto.getAmount().add(bankBookTransferDto.getAmount()));

        BankBookDto updateSource = bankBookService.update(sourceBankBookDto);
        BankBookDto updateTarget = bankBookService.update(targetBankBookDto);

        return updateSource != null && updateTarget != null;
    }

    @Override
    public void transferBetweenBankbooksUsers(UsersTransferDto usersTransferDto) {

        List<BankBookDto> bankBookTargetList = bankBookService.findByUserId(usersTransferDto.getUserTargetId());
        List<BankBookDto> bankBookSourceList = bankBookService.findByUserId(usersTransferDto.getUserSourceId());

        StringBuilder message = new StringBuilder();
        Boolean transactionSuccessful = false;

        for (BankBookDto bankBookTarget : bankBookTargetList) {
            for (BankBookDto bankBookSource : bankBookSourceList) {
                try {
                    transactionSuccessful = transferService.transferBetweenBankBooks(BankBookTransferDto.builder()
                            .bankBookSourceId(bankBookSource.getId())
                            .bankBookTargetId(bankBookTarget.getId())
                            .amount(usersTransferDto.getAmount())
                            .build());
                } catch (Exception e) {
                    message.append("Ошибка при отправке со счета отправителя[")
                            .append(bankBookTarget.getNumber())
                            .append("] на счет получателя[")
                            .append(bankBookSource.getNumber())
                            .append("] :")
                            .append(e.getMessage());
                }

                if (transactionSuccessful) {
                    break;
                }
            }

            if (transactionSuccessful) {
                break;
            }
        }

        if (!transactionSuccessful && !message.toString().isEmpty()) {
            throw new TransactionException(message.toString());
        }
    }
}
