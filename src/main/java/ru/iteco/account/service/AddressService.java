package ru.iteco.account.service;

import ru.iteco.account.model.dto.AddressDto;
import ru.iteco.account.model.entity.UserEntity;

public interface AddressService {

    UserEntity getUserByAddress(AddressDto addressDto);
}
