package ru.iteco.account.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("personalInformationServiceClass")
public class PersonalInformationService {

    @Value("${id}")
    private Integer id;

    public PersonalInfo getPersonalInfo(Integer id) {
        return new PersonalInfo(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
