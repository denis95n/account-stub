package ru.iteco.account.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import ru.iteco.account.aop.repository.ExternalRepository;
import ru.iteco.account.aop.repository.OtherRepository;
import ru.iteco.account.aop.repository.User;

@ComponentScan
@Slf4j
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Main.class);

        ExternalService externalService = annotationConfigApplicationContext.getBean("externalServiceImpl", ExternalService.class);
        ExternalService externalServiceOther = annotationConfigApplicationContext.getBean("externalServiceOther", ExternalService.class);

//        log.info("Result call externalServiceImpl.getInfo(): {}", externalService.getInfo(1L));
//        log.info("Result call externalServiceImpl.getCustomInfo(): {}", externalService.getCustomInfo());
//        log.info("Result call externalServiceOther.getInfo(): {}", externalServiceOther.getInfo(2L));


        ExternalRepository externalRepository = annotationConfigApplicationContext.getBean(ExternalRepository.class);
        String externalRepositoryInfo = externalRepository.getInfo("user1");
        log.info("externalRepository.getInfo() return: {}", externalRepositoryInfo);

        OtherRepository otherRepository = annotationConfigApplicationContext.getBean(OtherRepository.class);
        User user = new User(1, "user2");
        String otherRepositoryInfo = otherRepository.getInfo(user);

        log.info("otherRepository.getInfo() return: {}", otherRepositoryInfo);
        otherRepository.saveInfo(null);
    }

}
