package ru.iteco.account.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        AccountService bean = applicationContext.getBean(AccountService.class);

        PersonalInformationService personalInformationService = applicationContext.getBean(PersonalInformationService.class);

        PersonalInfo personalInfo = personalInformationService.getPersonalInfo(1);
        System.out.println(personalInfo.getId());
        System.out.println(bean);
    }

}
