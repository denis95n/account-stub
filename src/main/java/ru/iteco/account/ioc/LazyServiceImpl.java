package ru.iteco.account.ioc;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyServiceImpl implements LazyService {

    public boolean isLazy() {
        return true;
    }

}
