
package savingsandshoppingtracker;

import javax.swing.JOptionPane;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void viewBalance() {
        JOptionPane.showMessageDialog(null, String.format("Your current balance is: $%.2f", balance));
    }

    public void depositMoney() {
        String depositInput = JOptionPane.showInputDialog("Enter the amount to deposit:");
        double depositAmount;
        try {
            depositAmount = Double.parseDouble(depositInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount! Please try again.");
            return;
        }

        if (depositAmount <= 0) {
            JOptionPane.showMessageDialog(null, "Deposit amount must be greater than zero.");
        } else {
            balance += depositAmount;
            JOptionPane.showMessageDialog(null, String.format("Successfully deposited $%.2f.\nNew balance: $%.2f", depositAmount, balance));
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}