package Accounts;

public class LoanRequest {
    Account userAccount;
    double amount;

    public LoanRequest(Account userAccount, double amount) {
        this.userAccount = userAccount;
        this.amount = amount;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void approve() {
        userAccount.setBalance(userAccount.getBalance() + amount);
        userAccount.setLoan_amount(amount+userAccount.getLoan_amount());
    }
}
