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
        System.out.println(personalInformationService.getIntValueInfo());

        System.out.println(personalInformationService.getLazyProxyInfo());
        System.out.println(personalInformationService.getLazyInfo());

//        IntValue intValue = applicationContext.getBean(IntValue.class);
//        IntValue intValue1 = applicationContext.getBean(IntValue.class);
//
//        System.out.println(intValue);
//        System.out.println(intValue1);
//
//        System.out.println(intValue1.getInfo());
//        System.out.println(intValue.getInfo());
    }

}
