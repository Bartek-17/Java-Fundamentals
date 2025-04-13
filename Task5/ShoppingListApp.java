import java.util.Scanner;
import java.util.List;

public class ShoppingListApp {

    private ShoppingListController controller;

    public ShoppingListApp(String productFilePath, String shoppingListFilePath) {
        this.controller = new ShoppingListController(productFilePath, shoppingListFilePath);
    }

    public void run() {
        if (!controller.loadProductCategories()) {
            System.out.println("Failed to load product categories. Exiting the program.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Shopping List Menu ---");
            System.out.println("1. View Categories and Items");
            System.out.println("2. Add Item to Shopping List");
            System.out.println("3. View Shopping List");
            System.out.println("4. Remove Item from Shopping List");
            System.out.println("5. Save Shopping List");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> controller.displayProductCategories();
                    case 2 -> controller.addItem(scanner);
                    case 3 -> controller.displayShoppingList();
                    case 4 -> controller.removeItem(scanner);
                    case 5 -> controller.saveShoppingList();
                    case 6 -> running = false;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        ShoppingListApp app = new ShoppingListApp("products.txt", "shopping_list.txt");
        app.run();
    }
}
