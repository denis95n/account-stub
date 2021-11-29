package ru.iteco.account.homeworktwo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.account.homeworktwo.model.CheckRequest;
import ru.iteco.account.homeworktwo.model.ExternalInfo;

@Slf4j
@Component
public class ExternalInfoProcess implements Process {

    @Value("${id-not-process}")
    private Integer id;

    @Override
    @CheckRequest
    public Boolean run(ExternalInfo externalInfo) {
        if (id.equals(externalInfo.getId())) {
            log.info("Process not need: {}", externalInfo);
            return false;
        }
        log.info("Process with: {}", externalInfo);
        return true;
    }

}
