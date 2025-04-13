import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class FileHandler {
    private String productFilePath;
    private String shoppingListFilePath;

    public FileHandler(String productFilePath, String shoppingListFilePath) {
        this.productFilePath = productFilePath;
        this.shoppingListFilePath = shoppingListFilePath;
    }

    public List<ProductCategory> loadProductList() {
        List<ProductCategory> categories = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(productFilePath));
            String currentCategory = null;
            List<String> items = new ArrayList<>();

            for (String line : lines) {
                if (line.endsWith(":")) {
                    if (currentCategory != null) {
                        categories.add(new ProductCategory(currentCategory, new ArrayList<>(items)));
                    }
                    currentCategory = line.replace(":", "").trim();
                    items.clear();
                } else {
                    items.add(line.trim());
                }
            }
            // add last category
            if (currentCategory != null) {
                categories.add(new ProductCategory(currentCategory, items));
            }
        } catch (IOException e) {
            System.out.println("Error loading product file: " + e.getMessage());
            return null;
        }
        System.out.println(categories);
        return categories;
    }

    public boolean saveShoppingList(List<String> shoppingList) {
        try (PrintWriter writer = new PrintWriter(shoppingListFilePath)) {
            for (String item : shoppingList) {
                writer.println(item);
            }
            return true; // Return true if saving was successful
        } catch (IOException e) {
            System.out.println("Error saving shopping list: " + e.getMessage());
            return false; // Return false if an error occurred
        }
    }
}
