package Employees;

import Accounts.Account;
import Accounts.AccountType;
import Exceptions.UnauthorizedAccessException;

public class ManagingDirector extends Employee{
    public ManagingDirector(String name, String type) {
        super(name, type);
    }

    @Override
    public void change_interest(AccountType type, double interest_rate) throws UnauthorizedAccessException {
        type.setInterest_rate(interest_rate);
        System.out.println("Interest rate of "+type.getAccount_type()+" changed to "+interest_rate*100+"%");
    }

    @Override
    public void see_internal_fund() throws UnauthorizedAccessException {
        System.out.println("Internal fund is "+Bank.Bank.getInternal_fund());
    }
}
