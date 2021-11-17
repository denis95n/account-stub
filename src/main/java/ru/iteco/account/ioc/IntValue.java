package ru.iteco.account.ioc;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class IntValue {

    @InjectRandom
    private int value1;

    @InjectRandom(min = 10, max = 100)
    private int value2;

    @EncryptResult
    public String getInfo() {
        return "IntValue{" +
                "value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }
}
