package ru.iteco.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.iteco.account.model.dto.TransactionDto;
import ru.iteco.account.model.entity.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "status", source = "status.id")
    TransactionDto mapToDto(TransactionEntity transactionEntity);

    @Mapping(target = "status.id", source = "status")
    TransactionEntity mapToEntity(TransactionDto transactionDto);
}
