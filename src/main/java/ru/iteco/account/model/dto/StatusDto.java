package ru.iteco.account.model.dto;

import lombok.Data;
import ru.iteco.account.validation.Created;
import ru.iteco.account.validation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class StatusDto {

    @Null(groups = Created.class)
    @NotNull(groups = Update.class)
    Integer Id;
    @NotBlank
    String name;
}
