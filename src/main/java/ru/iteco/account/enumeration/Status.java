package ru.iteco.account.enumeration;

import lombok.Getter;

@Getter
public enum Status {
    PROCESSING(1),
    SUCCESSFUL(2),
    DECLINED(3);

    Integer id;

    Status(Integer id) {
        this.id = id;
    }
}
