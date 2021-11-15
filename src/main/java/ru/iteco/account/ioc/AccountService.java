package ru.iteco.account.ioc;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountService {

    private PersonalInformationService personalInformation;
    private BankBookService bankBookService;

    public AccountService(PersonalInformationService personalInformation,
                          BankBookService bankBookService) {
        this.personalInformation = personalInformation;
        this.bankBookService = bankBookService;
    }

    public AccountInfo getAccountInfoById(Integer id) {
        PersonalInfo personalInfo = personalInformation.getPersonalInfo(id);
        List<BankBookInfo> bankBookInfo = bankBookService.getBankBookInfo(id);
        return new AccountInfo(personalInfo, bankBookInfo);
    }

}
