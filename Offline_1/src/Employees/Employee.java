package Employees;

import Accounts.Account;
import Accounts.AccountType;
import Accounts.LoanRequest;
import Bank.Bank;
import Exceptions.UnauthorizedAccessException;

import java.util.Vector;

public abstract class Employee {
    private final String name;
    public enum EmployeeType {
        Cashier, Officer, Managing_Director
    }
    private final EmployeeType type;

    public Employee(String name, String type) {
        this.name = name;
        this.type = EmployeeType.valueOf(type);
    }

    public String getName() {
        return name;
    }

    public EmployeeType getType() {
        return type;
    }

    public void lookUp(Account userAccount) {
        System.out.println(userAccount.getUsername()+ "'s current balance is "+userAccount.getBalance()+" ");
    }

    public void approve_loan(Vector<LoanRequest> loan_requests) throws UnauthorizedAccessException
    {
        for(LoanRequest loanRequest: loan_requests)
        {
            loanRequest.approve();
            Bank.setInternal_fund(Bank.getInternal_fund() - loanRequest.getAmount());
        }
        //free vector
        loan_requests.clear();
        System.out.println("Loan requests approved");
    }
    public abstract void change_interest(AccountType type, double interest_rate) throws UnauthorizedAccessException;
    public abstract void see_internal_fund() throws UnauthorizedAccessException;
}
