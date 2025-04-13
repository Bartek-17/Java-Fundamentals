import java.util.List;

class ProductCategory {
    private String name;
    private List<String> items;

    public ProductCategory(String name, List<String> items) {
        this.name = name;
        this.items = items;
    }

    // returns object description in a form of string
    @Override
    public String toString() {
        return name + ": " + items;
    }

    public String getName() {
        return name;
    }

    public List<String> getItems() {
        return items;
    }
}
