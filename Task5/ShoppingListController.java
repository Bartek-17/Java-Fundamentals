import java.util.List;
import java.util.Scanner;

class ShoppingListController {
    private FileHandler fileHandler;
    private ShoppingListManager shoppingListManager;
    private List<ProductCategory> productCategories;

    public ShoppingListController(String productFilePath, String shoppingListFilePath) {
        this.fileHandler = new FileHandler(productFilePath, shoppingListFilePath);
        this.shoppingListManager = new ShoppingListManager();
    }

    public boolean loadProductCategories() {
        this.productCategories = fileHandler.loadProductList();
        return productCategories != null;
    }

    public void displayProductCategories() {
        System.out.println("\nAvailable Products:");
        for (ProductCategory category : productCategories) {
            System.out.println(category.getName() + ": " + String.join(", ", category.getItems()));
        }
    }

    public void addItem(Scanner scanner) {
        System.out.print("Enter the item name you want to add: ");
        String itemName = scanner.nextLine().trim();

        boolean found = false;
        for (ProductCategory category : productCategories) {
            if (category.getItems().contains(itemName)) {
                shoppingListManager.addItem(itemName);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found in any category.");
        }
    }

    public void displayShoppingList() {
        shoppingListManager.displayShoppingList();
    }

    public void removeItem(Scanner scanner) {
        shoppingListManager.displayShoppingList();
        if (!shoppingListManager.getShoppingList().isEmpty()) {
            System.out.print("Enter the item number to remove: ");
            if (scanner.hasNextInt()) {
                int itemIndex = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline
                shoppingListManager.removeItem(itemIndex);
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        } else {
            System.out.println("The shopping list is empty.");
        }
    }

    public void saveShoppingList() {
        if (fileHandler.saveShoppingList(shoppingListManager.getShoppingList())) {
            System.out.println("Shopping list saved successfully.");
        } else {
            System.out.println("Failed to save shopping list. Exiting.");
            System.exit(1);
        }
    }
}
