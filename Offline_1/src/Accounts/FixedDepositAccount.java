package Accounts;

import Exceptions.CustomException;
import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidAmountException;
import Bank.Bank;

public class FixedDepositAccount extends Account{
    public FixedDepositAccount(String username, AccountType type, double initial_deposit) throws InvalidAmountException {
        super(username, type, initial_deposit);
        if(initial_deposit < 100000)
            throw new InvalidAmountException("Initial deposit must be greater than 100000");
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException {
        if(amount > 50000)
        {
            setBalance(getBalance() + amount);
            Bank.setInternal_fund(Bank.getInternal_fund() + amount);
            System.out.println(amount+" deposited successfully. Your balance is "+getBalance()+" ");
        }
        else
            throw new InvalidAmountException("Deposit amount must be greater than 50000");
    }

    @Override
    public void withdraw(double amount) throws CustomException {
        if(amount < 0)
            throw new InvalidAmountException("Withdraw amount must be greater than 0");
        else if(amount > getBalance())
            throw new InsufficientBalanceException("Withdraw amount must be less than balance");
        else if(getPeriod()<Bank.FD_maturity_period)
            throw new CustomException("You cannot withdraw before maturity period");
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
        else if(amount > 100000)
            throw new InvalidAmountException("You cannot request more than 100000");
        else {
            System.out.println("Loan request successful, sent for approval");
            return new LoanRequest(this, amount);
        }
    }
}
