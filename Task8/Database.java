import com.google.gson.*;  //  used for working with JSON
import com.google.gson.reflect.TypeToken;  // TypeToken helps Gson understand the structure of the JSON data as a list of maps.

import java.io.*;
import java.lang.reflect.Type;  // Type allows the Gson library to deserialize the JSON file into a List<Map<String, Object>>
import java.util.*;

public class Database {
    private String filePath;
    private List<Map<String, Object>> data;

    public Database(String filePath) {
        this.filePath = filePath;
        loadData();
    }

    private void loadData() {
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
            this.data = new Gson().fromJson(reader, listType);
            if (this.data == null) this.data = new ArrayList<>();
            // Remove null or empty records
            this.data.removeIf(record -> record == null || record.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
            this.data = new ArrayList<>();
        }
    }

    private void saveData() {
        try (Writer writer = new FileWriter(filePath)) {
            new Gson().toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Select operation with optional WHERE clause and field selection
    public List<Map<String, Object>> select(String fields, String whereClause) {
        List<Map<String, Object>> selectedData = whereClause == null || whereClause.isEmpty()
                ? data.stream().filter(record -> !record.isEmpty()).toList()
                : WhereClause.evaluate(data, whereClause);

        if (fields == null || fields.equals("*")) {
            return selectedData;
        }

        List<Map<String, Object>> result = new ArrayList<>();
        String[] fieldNames = fields.split(",");

        for (Map<String, Object> record : selectedData) {
            if (record != null && !record.isEmpty()) {
                Map<String, Object> selectedRecord = new HashMap<>();
                for (String field : fieldNames) {
                    String trimmedField = field.trim();
                    if (record.containsKey(trimmedField)) {
                        selectedRecord.put(trimmedField, record.get(trimmedField));
                    }
                }
                result.add(selectedRecord);
            }
        }

        return result;
    }

    public void insert(Map<String, Object> record) {
        if (record != null && !record.isEmpty()) {
            data.add(record);
            saveData();
        }
    }

    public boolean update(String whereClause, Map<String, Object> updates) {
        List<Map<String, Object>> matches = WhereClause.evaluate(data, whereClause);
        if (matches.isEmpty()) return false;

        for (Map<String, Object> record : matches) {
            if (record != null) {
                record.putAll(updates);
            }
        }
        saveData();
        return true;
    }

    public boolean delete(String whereClause) {
        List<Map<String, Object>> matches = WhereClause.evaluate(data, whereClause);
        if (matches.isEmpty()) return false;

        data.removeAll(matches);
        saveData();
        return true;
    }
}
