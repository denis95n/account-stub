package ru.iteco.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class BankBookTransferDto {

    @NotNull
    Integer bankBookSourceId;

    @NotNull
    Integer bankBookTargetId;

    @PositiveOrZero
    BigDecimal amount;
}
