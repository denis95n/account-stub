package ru.iteco.account.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.iteco.account.enumeration.Status;
import ru.iteco.account.mapper.TransactionMapper;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.model.dto.TransferDto;
import ru.iteco.account.model.dto.TransactionDto;
import ru.iteco.account.model.exception.TransactionException;
import ru.iteco.account.repository.TransactionRepository;
import ru.iteco.account.service.BankBookService;

import java.time.LocalDateTime;

@Aspect
@Component
public class AnnotationTransferTransaction {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final BankBookService bankBookService;

    public AnnotationTransferTransaction(TransactionRepository transactionRepository,
                                         TransactionMapper transactionMapper,
                                         BankBookService bankBookService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.bankBookService = bankBookService;
    }

    @Around(value = "annotationTransferTransaction() && bankBookTransferArgument(transferDto)", argNames = "joinPoint,transferDto")
    public Object aroundAnnotationTransferTransactionAdvice(ProceedingJoinPoint joinPoint,
                                                            TransferDto transferDto) throws Throwable {

        TransactionDto transactionDto = buildTransactionDto(transferDto);

        transactionRepository.save(transactionMapper.mapToEntity(transactionDto));

        checkTransaction(transactionDto);

        Object proceed = joinPoint.proceed();

        transactionDto.setStatus(Status.SUCCESSFUL.getId());
        transactionDto.setCompletionDate(LocalDateTime.now());
        transactionRepository.save(transactionMapper.mapToEntity(transactionDto));

        return proceed;
    }

    private TransactionDto buildTransactionDto(TransferDto transferDto) {

        TransactionDto transactionDto = TransactionDto.builder()
                .initiationDate(LocalDateTime.now())
                .status(Status.PROCESSING.getId())
                .build();

        transactionDto.setTargetBankBookId(transferDto.getTargetId());
        transactionDto.setSourceBankBookId(transferDto.getSourceId());
        transactionDto.setAmount(transferDto.getAmount());


        return transactionDto;
    }

    private void checkTransaction(TransactionDto transactionDto) {

        BankBookDto sourceBankBookDto = bankBookService.findById(transactionDto.getSourceBankBookId());
        BankBookDto targetBankBookDto = bankBookService.findById(transactionDto.getTargetBankBookId());

        if (sourceBankBookDto.equals(targetBankBookDto)) {
            transactionDto.setStatus(Status.DECLINED.getId());
            transactionRepository.save(transactionMapper.mapToEntity(transactionDto));
            throw new TransactionException("Неверный счет отправителя.");
        }

        if (!sourceBankBookDto.getCurrency().equals(targetBankBookDto.getCurrency())) {
            transactionDto.setStatus(Status.DECLINED.getId());
            transactionRepository.save(transactionMapper.mapToEntity(transactionDto));
            throw new TransactionException("Валюта отправителя должна совподать с валютой получателя.");
        }

        if (sourceBankBookDto.getAmount().compareTo(transactionDto.getAmount()) < 0) {
            transactionDto.setStatus(Status.DECLINED.getId());
            transactionRepository.save(transactionMapper.mapToEntity(transactionDto));
            throw new TransactionException("На счете отправителя недостаточно средств.");
        }
    }


    @Pointcut("@annotation(ru.iteco.account.annotation.BankBookTransferTransaction)")
    public void annotationTransferTransaction() {
    }

    @Pointcut("args(transferDto)")
    public void bankBookTransferArgument(TransferDto transferDto) {
    }
}
