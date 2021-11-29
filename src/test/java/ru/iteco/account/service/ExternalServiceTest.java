package ru.iteco.account.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternalServiceTest {

    private ExternalService externalService = new ExternalService();

    @Test
    void testGetInfo() {
        String expectedInfo = "INFO with 1";
        String actualInfo = externalService.getInfo("1");
        assertEquals(expectedInfo, actualInfo);
    }

}