import java.util.*;

public class Main {
    public static void main(String[] args) {
        Database db = new Database("data.json");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the File-Based Database!");
        System.out.println("Type SQL-like commands or 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase("exit")) break;

            if (command.startsWith("select")) {
                String[] parts = command.split("from");
                String fields = parts[0].replace("select", "").trim();
                String tableName = parts[1].split("where")[0].trim();
                String whereClause = null;

                if (command.contains("where")) {
                    whereClause = parseWhereClause(command);
                }

                List<Map<String, Object>> results = db.select(fields, whereClause);
                results.forEach(System.out::println);
            } else if (command.startsWith("insert")) {
                Map<String, Object> record = parseInsert(command);
                db.insert(record);
                System.out.println("Record inserted.");
            } else if (command.startsWith("update")) {
                String whereClause = parseWhereClause(command);
                Map<String, Object> updates = parseUpdate(command);
                if (db.update(whereClause, updates)) {
                    System.out.println("Record(s) updated.");
                } else {
                    System.out.println("No matching records found.");
                }
            } else if (command.startsWith("delete")) {
                String whereClause = parseWhereClause(command);
                if (db.delete(whereClause)) {
                    System.out.println("Record(s) deleted.");
                } else {
                    System.out.println("No matching records found.");
                }
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    private static String parseWhereClause(String command) {
        if (command.contains("where")) {
            return command.split("where")[1].trim();
        }
        return null;
    }

    private static Map<String, Object> parseInsert(String command) {
        String values = command.split("values")[1].trim();
        Map<String, Object> record = new HashMap<>();
        for (String pair : values.substring(1, values.length() - 1).split(",")) {
            String[] keyValue = pair.split("=");
            record.put(keyValue[0].trim(), keyValue[1].trim().replace("'", ""));
        }
        return record;
    }

    private static Map<String, Object> parseUpdate(String command) {
        String updates = command.split("set")[1].split("where")[0].trim();
        Map<String, Object> updateMap = new HashMap<>();
        for (String pair : updates.split(",")) {
            String[] keyValue = pair.split("=");
            updateMap.put(keyValue[0].trim(), keyValue[1].trim().replace("'", ""));
        }
        return updateMap;
    }
}
