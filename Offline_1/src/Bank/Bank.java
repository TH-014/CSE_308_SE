package Bank;

import Accounts.*;
import Employees.Cashier;
import Employees.Employee;
import Employees.ManagingDirector;
import Employees.Officer;
import Exceptions.CustomException;

import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

public class Bank {
    public static int FD_maturity_period = 2;
    private static double internal_fund = 1000000;
    private static String activeClient = "";
    public Vector<LoanRequest> loan_requests = new Vector<>();
    public HashMap<String, Account> accounts = new HashMap<>();
    public HashMap<String, AccountType> accountTypesMap = new HashMap<>();
    public HashMap<String, Employee> employees = new HashMap<>();
    public static double getInternal_fund() {
        return internal_fund;
    }

    public static void setInternal_fund(double internal_fund) {
        Bank.internal_fund = internal_fund;
    }

    public void setInternal_fund(int internal_fund) {
        Bank.internal_fund = internal_fund;
    }
    public Bank() {
        accountTypesMap.put("Savings", new AccountType("Savings"));
        accountTypesMap.put("Student", new AccountType("Student"));
        accountTypesMap.put("Fixed_Deposit", new AccountType("Fixed_Deposit"));

        employees.put("MD", new ManagingDirector("MD", "Managing_Director"));
        employees.put("S1", new Officer("S1", "Officer"));
        employees.put("S2", new Officer("S2", "Officer"));
        employees.put("C1", new Cashier("C1", "Cashier"));
        employees.put("C2", new Cashier("C2", "Cashier"));
        employees.put("C3", new Cashier("C3", "Cashier"));
        employees.put("C4", new Cashier("C4", "Cashier"));
        employees.put("C5", new Cashier("C5", "Cashier"));
        System.out.println("Banking System created. MD, S1, S2, C1, C2, C3, C4, C5 created");
    }

    public void IncreamentTime() {
        System.out.println("1 year has passed");
        for (Account account : accounts.values()) {
            account.apply_interest();
        }
    }

    public void createAccount(String username, String accountType, double balance) throws Exception {

        if (accounts.containsKey(username)) {
            throw new Exception("Account already exists");
        }
        AccountType type = accountTypesMap.get(accountType);
        Account account;
        if (type == null) {
            throw new Exception("Invalid account type");
        }
        account = switch (accountType) {
            case "Fixed_Deposit" -> new FixedDepositAccount(username, type, balance);
            case "Savings" -> new SavingsAccount(username, type, balance);
            case "Student" -> new StudentsAccount(username, type, balance);
            default -> throw new CustomException("Invalid account type");
        };
        accounts.put(username, account);
        activeClient = username;
        internal_fund += balance;
        System.out.println(accountType+" account for "+username +" created; initial balance "+balance);
    }
    public void ExecuteCommands(Bank bank, String line)
    {
        String [] commands = line.split(" ");
        LoanRequest lr = null;
        try{
            String cmd = commands[0].toLowerCase();
            switch(cmd){
                case "create":
                    if(!Objects.equals(activeClient, ""))
                    {
                        System.out.println("Transaction already open for "+activeClient);
                        break;
                    }
                    try {
                        bank.createAccount(commands[1], commands[2], Double.parseDouble(commands[3]));
                    } catch (CustomException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deposit":
                    if(Objects.equals(activeClient, ""))
                        System.out.println("No active transaction");
                    else if(!accounts.containsKey(activeClient))
                        System.out.println("Account does not exist!");
                    else
                        accounts.get(activeClient).deposit(Double.parseDouble(commands[1]));
                    break;
                case "withdraw":
                    if(Objects.equals(activeClient, ""))
                        System.out.println("No active transaction");
                    else if(!accounts.containsKey(activeClient))
                        System.out.println("Account does not exist!");
                    else
                        accounts.get(activeClient).withdraw(Double.parseDouble(commands[1]));
                    break;
                case "request":
                    if(Objects.equals(activeClient, ""))
                    {
                        System.out.println("No active transaction");
                        break;
                    }
                    else if(!accounts.containsKey(activeClient))
                    {
                        System.out.println("Account does not exist!");
                        break;
                    }
                    else if(accounts.get(activeClient).getLoan_amount() > 0)
                    {
                        System.out.println("Loan already taken!");
                        break;
                    }
                    lr = accounts.get(activeClient).request_loan(Double.parseDouble(commands[1]));
                    if(lr != null)
                        loan_requests.add(lr);
                    break;
                case "query":
                    if(Objects.equals(activeClient, ""))
                    {
                        System.out.println("No active transaction");
                        break;
                    }
                    else if(!accounts.containsKey(activeClient))
                    {
                        System.out.println("Account does not exist!");
                        break;
                    }
                    accounts.get(activeClient).query_deposit();
                    break;
                case "approve":
                    if(Objects.equals(activeClient, ""))
                    {
                        System.out.println("No active employee");
                        break;
                    }
                    else if(!employees.containsKey(activeClient))
                    {
                        System.out.println("Unauthorized access!");
                        break;
                    }
                    employees.get(activeClient).approve_loan(loan_requests);
                    break;
                case "change":
                    if(Objects.equals(activeClient, ""))
                    {
                        System.out.println("No active employee");
                        break;
                    }
                    else if(!employees.containsKey(activeClient))
                    {
                        System.out.println("Unauthorized access!");
                        break;
                    }
                    employees.get(activeClient).change_interest(accountTypesMap.get(commands[1]), (Double.parseDouble(commands[2])/100.0));
                    break;
                case "lookup":
                    if(Objects.equals(activeClient, ""))
                    {
                        System.out.println("No active employee");
                        break;
                    }
                    else if(!employees.containsKey(activeClient))
                    {
                        System.out.println("Unauthorized access!");
                        break;
                    }
                    employees.get(activeClient).lookUp(accounts.get(commands[1]));
                    break;
                case "see":
                    if(Objects.equals(activeClient, ""))
                    {
                        System.out.println("No active employee");
                        break;
                    }
                    else if(!employees.containsKey(activeClient))
                    {
                        System.out.println("Unauthorized access!");
                        break;
                    }
                    employees.get(activeClient).see_internal_fund();
                    break;
                case "inc":
                    bank.IncreamentTime();
                    break;
                case "open":
                    if(!Objects.equals(activeClient, ""))
                        System.out.println("Transaction already open for "+activeClient);
                    //check if account exists
                    if(accounts.containsKey(commands[1]))
                    {
                        System.out.println("Welcome back, "+commands[1]);
                        activeClient = commands[1];
                    }
                    else if(employees.containsKey(commands[1]))
                    {
                        System.out.print(commands[1]+" active ");
                        if(!loan_requests.isEmpty())
                            System.out.println(", there are loan requests pending");
                        else
                            System.out.println();
                        activeClient = commands[1];
                    }
                    else
                        System.out.println("Account does not exist");
                    break;
                case "close":
                    if(Objects.equals(activeClient, ""))
                        System.out.println("No active transaction");
                    else
                        System.out.println("Transaction closed for "+activeClient);
                    activeClient="";
                    break;
                case "exit":
                    System.out.println("Exiting Banking System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
