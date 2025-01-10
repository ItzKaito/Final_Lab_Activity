
package savingsandshoppingtracker;

public class Receipt {
    private StringBuilder receipt = new StringBuilder("Receipt:\n");

    public void addPurchase(String item, double price) {
        receipt.append(String.format("%-30s $%.2f\n", item, price));
    }

    public String getReceipt() {
        return receipt.length() > 8 ? receipt.toString() : "No purchases made yet!";
    }
}
