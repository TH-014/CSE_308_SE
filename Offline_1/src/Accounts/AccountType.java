package Accounts;

public class AccountType {
    public enum type {
        Savings,
        Students,
        Fixed_Deposit
    }
    type account_type;
    double interest_rate;
    double loan_interest_rate;

    public AccountType(String account_type) {
        if (account_type.equalsIgnoreCase("Savings")) {
            this.account_type = type.Savings;
            interest_rate = 0.1;
        } else if (account_type.equalsIgnoreCase("Student")) {
            this.account_type = type.Students;
            interest_rate = 0.05;
        } else if (account_type.equalsIgnoreCase("Fixed Deposit")) {
            this.account_type = type.Fixed_Deposit;
            interest_rate = 0.15;
        }
        this.loan_interest_rate = 0.1;
    }

    public type getAccount_type() {
        return account_type;
    }

    public void setAccount_type(type account_type) {
        this.account_type = account_type;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public double getLoan_interest_rate() {
        return loan_interest_rate;
    }

    public void setLoan_interest_rate(double loan_interest_rate) {
        this.loan_interest_rate = loan_interest_rate;
    }
}
