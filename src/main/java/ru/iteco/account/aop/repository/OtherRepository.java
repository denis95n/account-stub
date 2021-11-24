package ru.iteco.account.aop.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OtherRepository {

    public String getInfo(User user) {
        log.info("Call OtherRepository.getInfo()");
        return "OTHER_INFO";
    }

    public void saveInfo(String info) {
        if (info == null) {
            throw new RuntimeException("NULL not saved!");
        }
        log.info("SAVE INFO: {}", info);
    }

}
