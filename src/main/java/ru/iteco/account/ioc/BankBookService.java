package ru.iteco.account.ioc;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class BankBookService {

    public List<BankBookInfo> getBankBookInfo(Integer id) {
        return new ArrayList<>();
    }

}
