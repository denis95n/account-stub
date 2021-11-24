package ru.iteco.account.aop.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExternalRepository {

    public void setInfo(String info) {
        log.info("Set info in ExternalRepository. Info: {}", info);
    }

    public String getInfo(String user) {
        if ("user1".equals(user)) {
            return "EXTERNAL_INFO";
        } else {
            throw new RuntimeException("User not access!");
        }
    }

}
