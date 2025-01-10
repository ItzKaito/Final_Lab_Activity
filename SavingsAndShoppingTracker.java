
package savingsandshoppingtracker;

import javax.swing.JOptionPane;

public class SavingsAndShoppingTracker {
    private static BankAccount bankAccount = new BankAccount(1000.00);  // Initial balance
    private static Cart cart = new Cart();

    public static double getBankBalance() {
        return bankAccount.getBalance();
    }

    public static void setBankBalance(double newBalance) {
        bankAccount.setBalance(newBalance);
    }

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {

            String menu = """
                    Select an option:
                    1. View Balance
                    2. Shop for Items
                    3. Deposit Money
                    4. View Receipt
                    5. Exit
                    """;
            String input = JOptionPane.showInputDialog(menu);
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1: 
                    bankAccount.viewBalance();
                    break;

                case 2:
                    cart.shop();
                    break;

                case 3: 
                    bankAccount.depositMoney();
                    break;

                case 4: 
                    cart.viewReceipt();
                    break;

                case 5: 
                    JOptionPane.showMessageDialog(null, "Thank you for using the savings and shopping tracker. Goodbye!");
                    exit = true;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option! Please select a number between 1 and 5.");
            }
        }
    }
}
