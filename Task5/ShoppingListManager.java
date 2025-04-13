import java.util.ArrayList;
import java.util.List;

class ShoppingListManager {
    private List<String> shoppingList;

    public ShoppingListManager() {
        this.shoppingList = new ArrayList<>();
    }

    public void addItem(String item) {
        shoppingList.add(item);
        System.out.println("Added: " + item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < shoppingList.size()) {
            System.out.println("Removed: " + shoppingList.remove(index));
        } else {
            System.out.println("Invalid item number.");
        }
    }

    public void displayShoppingList() {
        if (shoppingList.isEmpty()) {
            System.out.println("Shopping list is empty.");
        } else {
            System.out.println("Your Shopping List:");
            for (int i = 0; i < shoppingList.size(); i++) {
                System.out.println((i + 1) + ". " + shoppingList.get(i));
            }
        }
    }

    public List<String> getShoppingList() {
        return shoppingList;
    }
}
