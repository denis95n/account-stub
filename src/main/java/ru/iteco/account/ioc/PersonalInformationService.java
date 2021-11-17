package ru.iteco.account.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PersonalInformationService {

    private final IntValue intValue;
    private final LazyService lazyService;

    @Value("${id}")
    private Integer id;

    public PersonalInformationService(IntValue intValue, @Lazy LazyService lazyService) {
        this.intValue = intValue;
        this.lazyService = lazyService;
    }

    public PersonalInfo getPersonalInfo(Integer id) {
        return new PersonalInfo(id);
    }

    public String getLazyProxyInfo() {
        return lazyService.getClass().toString();
    }

    public boolean getLazyInfo() {
        return lazyService.isLazy();
    }

    public String getIntValueInfo() {
        return intValue.getInfo();
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
