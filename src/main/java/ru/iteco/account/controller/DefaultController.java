package ru.iteco.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.account.service.ExternalService;

@RestController
public class DefaultController {

    private final ExternalService externalService;

    public DefaultController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/")
    public String getInfo() {
        return externalService.getInfo("1");
    }

}
