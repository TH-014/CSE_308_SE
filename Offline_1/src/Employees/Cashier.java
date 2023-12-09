package Employees;

import Accounts.Account;
import Accounts.AccountType;
import Accounts.LoanRequest;
import Exceptions.UnauthorizedAccessException;

import java.util.Vector;

public class Cashier extends Employee{
    public Cashier(String name, String type) {
        super(name, type);
    }

    @Override
    public void approve_loan(Vector<LoanRequest> loan_requests) throws UnauthorizedAccessException {
        throw new UnauthorizedAccessException("Cashier cannot approve loan");
    }

    @Override
    public void change_interest(AccountType type, double interest_rate) throws UnauthorizedAccessException {
        throw new UnauthorizedAccessException("Cashier cannot change interest rate");
    }

    @Override
    public void see_internal_fund() throws UnauthorizedAccessException {
        throw new UnauthorizedAccessException("Cashier cannot see internal fund");
    }
}
