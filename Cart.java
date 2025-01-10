
package savingsandshoppingtracker;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Cart {
    private ArrayList<String> cartItems = new ArrayList<>();
    private ArrayList<Double> cartPrices = new ArrayList<>();
    private ArrayList<Integer> cartQuantities = new ArrayList<>();
    private Receipt receipt = new Receipt();

    public void shop() {
        boolean shopping = true;
        while (shopping) {
            String shopMenu = """
                    Welcome to the shop! Select an item to add to your cart:
                    1. Apple ($2.50)
                    2. Bread ($3.00)
                    3. Milk ($1.75)
                    4. Chocolate ($4.25)
                    5. View Cart
                    6. Remove Item from Cart
                    7. Exit Shop
                    """;
            String shopChoiceInput = JOptionPane.showInputDialog(shopMenu);
            int shopChoice;

            try {
                shopChoice = Integer.parseInt(shopChoiceInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number between 1 and 7.");
                continue;
            }

            switch (shopChoice) {
                case 1: 
                    addItemToCart("Apple", 2.50);
                    break;

                case 2: 
                    addItemToCart("Bread", 3.00);
                    break;

                case 3: 
                    addItemToCart("Milk", 1.75);
                    break;

                case 4: 
                    addItemToCart("Chocolate", 4.25);
                    break;

                case 5: 
                    viewCart();
                    break;

                case 6: 
                    removeItemFromCart();
                    break;

                case 7: 
                    shopping = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option! Please select a number between 1 and 7.");
            }
        }
    }

    private void addItemToCart(String itemName, double itemPrice) {
        String quantityInput = JOptionPane.showInputDialog("Enter the quantity for " + itemName + ":");
        int quantity;
        try {
            quantity = Integer.parseInt(quantityInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity! Please try again.");
            return;
        }

        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity must be greater than zero.");
        } else {
            cartItems.add(itemName);
            cartPrices.add(itemPrice);
            cartQuantities.add(quantity);
            JOptionPane.showMessageDialog(null, String.format("%d x %s added to your cart.", quantity, itemName));
        }
    }

    private void viewCart() {
        StringBuilder cartSummary = new StringBuilder("Your cart:\n");
        double cartTotal = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            double totalItemPrice = cartPrices.get(i) * cartQuantities.get(i);
            cartSummary.append(String.format("%-20s Qty: %d  $%.2f\n", cartItems.get(i), cartQuantities.get(i), totalItemPrice));
            cartTotal += totalItemPrice;
        }

        cartSummary.append(String.format("\nTotal: $%.2f", cartTotal));

        int purchaseChoice = JOptionPane.showConfirmDialog(null, cartSummary.toString() + "\nDo you want to buy these items?", "Confirm Purchase", JOptionPane.YES_NO_OPTION);

        if (purchaseChoice == JOptionPane.YES_OPTION) {
            if (cartTotal > SavingsAndShoppingTracker.getBankBalance()) {
                JOptionPane.showMessageDialog(null, "Insufficient funds! You cannot buy these items.");
            } else {
                SavingsAndShoppingTracker.setBankBalance(SavingsAndShoppingTracker.getBankBalance() - cartTotal); // Update the balance
                for (int i = 0; i < cartItems.size(); i++) {
                    receipt.addPurchase(cartItems.get(i) + " (Qty: " + cartQuantities.get(i) + ")", cartPrices.get(i) * cartQuantities.get(i));
                }
                JOptionPane.showMessageDialog(null, String.format("Purchase successful! Remaining balance: $%.2f", SavingsAndShoppingTracker.getBankBalance()));
                cartItems.clear();
                cartPrices.clear();
                cartQuantities.clear();
            }
        } else {
            JOptionPane.showMessageDialog(null, "You chose not to buy the items.");
        }
    }

    private void removeItemFromCart() {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Your cart is empty. There are no items to remove.");
            return;
        }

        StringBuilder cartList = new StringBuilder("Items in your cart:\n");
        for (int i = 0; i < cartItems.size(); i++) {
            cartList.append(i + 1).append(". ").append(cartItems.get(i))
                    .append(" - Qty: ").append(cartQuantities.get(i))
                    .append(" - $").append(cartPrices.get(i) * cartQuantities.get(i))
                    .append("\n");
        }

        String itemToRemoveInput = JOptionPane.showInputDialog(cartList.toString() + "\nEnter the number of the item you want to remove:");

        int itemIndex;
        try {
            itemIndex = Integer.parseInt(itemToRemoveInput) - 1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
            return;
        }

        if (itemIndex < 0 || itemIndex >= cartItems.size()) {
            JOptionPane.showMessageDialog(null, "Invalid item number! Please try again.");
        } else {
            cartItems.remove(itemIndex);
            cartPrices.remove(itemIndex);
            cartQuantities.remove(itemIndex);
            JOptionPane.showMessageDialog(null, "Item removed from your cart.");
        }
    }

    public void viewReceipt() {
        String receiptString = receipt.getReceipt();
        JOptionPane.showMessageDialog(null, receiptString);
    }
}