package ru.iteco.account.aop.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OtherRepository {

    public String getInfo() {
        log.info("Call OtherRepository.getInfo()");
        return "OTHER_INFO";
    }

}
