package ru.iteco.account.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.iteco.account.validation.Created;
import ru.iteco.account.validation.Status;
import ru.iteco.account.validation.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {

    @Null(groups = Created.class)
    @NotNull(groups = Update.class)
    Integer id;

    @NotNull
    Integer sourceBankBookId;

    @NotNull
    Integer targetBankBookId;

    @NotNull
    @PositiveOrZero
    BigDecimal amount;

    @NotNull
    LocalDateTime initiationDate;
    LocalDateTime completionDate;

    @NotNull
    @Status
    Integer status;
}
