import Bank.Bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            bank.ExecuteCommands(bank, line);
        }
    }
}