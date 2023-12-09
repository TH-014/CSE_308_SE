package Employees;

import Accounts.Account;
import Accounts.AccountType;
import Exceptions.UnauthorizedAccessException;

public class Officer extends Employee{
    public Officer(String name, String type) {
        super(name, type);
    }

    @Override
    public void change_interest(AccountType type, double interest_rate) throws UnauthorizedAccessException {
        throw new UnauthorizedAccessException("Officer cannot change interest rate");
    }

    @Override
    public void see_internal_fund() throws UnauthorizedAccessException {
        throw new UnauthorizedAccessException("Officer cannot see internal fund");
    }
}
