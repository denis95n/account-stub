package ru.iteco.account.aop;

import org.springframework.stereotype.Component;

@Component
public class ExternalServiceOther implements ExternalService {
    @Override
    public String getInfo(Long id) {
        return "OTHER_INFO with id: " + id;
    }

    @Override
    public String getCustomInfo() {
        return null;
    }
}
