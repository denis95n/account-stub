package ru.iteco.account.aop.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExternalRepository {

    public void setInfo(String info) {
        log.info("Set info in ExternalRepository. Info: {}", info);
    }

}
