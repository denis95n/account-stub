package ru.iteco.account.ioc;

import java.util.List;

public class AccountInfo {

    private PersonalInfo personalInfo;
    private List<BankBookInfo> bankBookInfos;

    public AccountInfo(PersonalInfo personalInfo, List<BankBookInfo> bankBookInfos) {
        this.personalInfo = personalInfo;
        this.bankBookInfos = bankBookInfos;
    }

}
