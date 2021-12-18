package ru.iteco.account.validation;

import ru.iteco.account.model.entity.StatusEntity;
import ru.iteco.account.repository.StatusRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

public class StatusValidator implements ConstraintValidator<Status, Integer> {

    private final StatusRepository statusRepository;

    public StatusValidator(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {

        Optional<StatusEntity> status = statusRepository.findById(id);

        return status.filter(statusEntity ->
                Objects.equals(statusEntity.getId(), ru.iteco.account.enumeration.Status.PROCESSING.getId())).isPresent();

    }
}
