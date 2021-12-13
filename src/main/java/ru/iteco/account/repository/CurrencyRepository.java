package ru.iteco.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.account.model.entity.CurrencyEntity;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {

    CurrencyEntity findByName(String name);

}
