package ru.iteco.account.service.impl;

import org.springframework.stereotype.Service;
import ru.iteco.account.annotation.BankBookTransferTransaction;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.model.dto.TransferDto;
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
    public Boolean transferBetweenBankBooks(TransferDto transferDto) {

        BankBookDto sourceBankBookDto = bankBookService.findById(transferDto.getSourceId());
        BankBookDto targetBankBookDto = bankBookService.findById(transferDto.getTargetId());

        sourceBankBookDto.setAmount(sourceBankBookDto.getAmount().subtract(transferDto.getAmount()));
        targetBankBookDto.setAmount(targetBankBookDto.getAmount().add(transferDto.getAmount()));

        BankBookDto updateSource = bankBookService.update(sourceBankBookDto);
        BankBookDto updateTarget = bankBookService.update(targetBankBookDto);

        return updateSource != null && updateTarget != null;
    }

    @Override
    public void transferBetweenBankbooksUsers(TransferDto transferDto) {

        List<BankBookDto> bankBookTargetList = bankBookService.findByUserId(transferDto.getTargetId());
        List<BankBookDto> bankBookSourceList = bankBookService.findByUserId(transferDto.getSourceId());

        StringBuilder message = new StringBuilder();
        Boolean transactionSuccessful = false;

        for (BankBookDto bankBookTarget : bankBookTargetList) {
            for (BankBookDto bankBookSource : bankBookSourceList) {
                try {
                    transactionSuccessful = transferService.transferBetweenBankBooks(TransferDto.builder()
                            .sourceId(bankBookSource.getId())
                            .targetId(bankBookTarget.getId())
                            .amount(transferDto.getAmount())
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
