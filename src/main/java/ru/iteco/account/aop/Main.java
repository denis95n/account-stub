package ru.iteco.account.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.iteco.account.aop.repository.ExternalRepository;
import ru.iteco.account.aop.repository.OtherRepository;

@ComponentScan
@Slf4j
@EnableAspectJAutoProxy
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Main.class);

        ExternalService externalService = annotationConfigApplicationContext.getBean("externalServiceImpl", ExternalService.class);
        ExternalService externalServiceOther = annotationConfigApplicationContext.getBean("externalServiceOther", ExternalService.class);

        log.info("Result call externalServiceImpl.getInfo(): {}", externalService.getInfo(1L));
        log.info("Result call externalServiceImpl.getCustomInfo(): {}", externalService.getCustomInfo());
        log.info("Result call externalServiceOther.getInfo(): {}", externalServiceOther.getInfo(2L));


        ExternalRepository externalRepository = annotationConfigApplicationContext.getBean(ExternalRepository.class);
        externalRepository.setInfo("INFO IN REPO");

        OtherRepository otherRepository = annotationConfigApplicationContext.getBean(OtherRepository.class);
        otherRepository.getInfo();

    }

}
