package Accounts;

import Exceptions.CustomException;
import Exceptions.InvalidAmountException;

public abstract class Account {
    private String username;

    private AccountType type;
    private double balance;
    private double loan_amount;
    private int period;

    public Account(String username, AccountType type, double balance) throws InvalidAmountException {
        if(balance < 0)
            throw new InvalidAmountException("Initial deposit must be greater than 0");
        this.username = username;
        this.type = type;
        this.balance = balance;
        this.loan_amount = 0;
        this.period = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void apply_interest()
    {
        period++;
        balance += balance * type.getInterest_rate();
        if(loan_amount > 0)
            balance -= loan_amount * type.getLoan_interest_rate();
    }

    public abstract void deposit(double amount) throws InvalidAmountException;
    public abstract void withdraw(double amount) throws CustomException;
    public abstract LoanRequest request_loan(double amount) throws InvalidAmountException;
    public void query_deposit()
    {
        System.out.print("Current balance "+balance);
        if(loan_amount > 0)
            System.out.println(", Loan "+loan_amount);
        else
            System.out.println();
    }
}
