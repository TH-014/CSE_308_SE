package Accounts;

import Bank.Bank;
import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidAmountException;

public class SavingsAccount extends Account{
    public SavingsAccount(String username, AccountType type, double initial_deposit) throws InvalidAmountException {
        super(username, type, initial_deposit);
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if(amount > 0)
        {
            Bank.setInternal_fund(Bank.getInternal_fund() + amount);
            setBalance(getBalance() + amount);
            System.out.println(amount+" deposited successfully. Your balance is "+getBalance()+" ");
        }
        else
            throw new InvalidAmountException("Deposit amount must be greater than 0");
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if(amount < 0)
            throw new InvalidAmountException("Withdraw amount must be greater than 0");
        else if(amount > getBalance()-1000)
            throw new InsufficientBalanceException("You must have at least 1000 in your account");
        else
        {
            setBalance(getBalance() - amount);
            Bank.setInternal_fund(Bank.getInternal_fund() - amount);
            System.out.println(amount+" withdrawn. Your balance is "+getBalance()+" ");
        }
    }

    @Override
    public LoanRequest request_loan(double amount) throws InvalidAmountException {
        if(amount < 0)
            throw new InvalidAmountException("Loan amount must be greater than 0");
        else if(amount > 10000)
            throw new InvalidAmountException("You cannot request more than 10000");
        else {
            System.out.println("Loan request successful, sent for approval");
            return new LoanRequest(this, amount);
        }
    }
}
