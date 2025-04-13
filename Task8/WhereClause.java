import java.util.*;
import java.util.stream.Collectors;

public class WhereClause {
    public static List<Map<String, Object>> evaluate(List<Map<String, Object>> data, String whereClause) {
        return data.stream().filter(record -> evaluateRecord(record, whereClause)).collect(Collectors.toList());
    }

    private static boolean evaluateRecord(Map<String, Object> record, String whereClause) {
        String[] conditions = whereClause.split("and");
        for (String condition : conditions) {
            condition = condition.trim();

            if (condition.contains("like")) {
                String[] keyValue = condition.split("like");
                if (keyValue.length != 2) return false;

                String key = keyValue[0].trim();
                String value = keyValue[1].trim().replace("'", "");
                Object recordValue = record.getOrDefault(key, null);

                if (recordValue == null) {
                    return false; // Field does not exist
                }

                if (!isLike(recordValue.toString(), value)) {
                    return false;
                }
            } else if (condition.contains("=")) {
                String[] keyValue = condition.split("=");
                if (keyValue.length != 2) return false;

                String key = keyValue[0].trim();
                String value = keyValue[1].trim().replace("'", "");
                Object recordValue = record.getOrDefault(key, null);

                if (recordValue == null) {
                    return false; // Field does not exist
                }

                if (!isEqual(recordValue, value)) {
                    return false;
                }
            } else if (condition.contains(">") || condition.contains("<")) {
                // Handle greater than or less than
                String operator = condition.contains(">") ? ">" : "<";
                String[] keyValue = condition.split(operator);
                if (keyValue.length != 2) return false;

                String key = keyValue[0].trim();
                String value = keyValue[1].trim().replace("'", "");
                Object recordValue = record.getOrDefault(key, null);

                if (recordValue == null) {
                    return false; // Field does not exist
                }

                if (operator.equals(">")) {
                    if (!isGreaterThan(recordValue, value)) {
                        return false;
                    }
                } else if (operator.equals("<")) {
                    if (!isLessThan(recordValue, value)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isEqual(Object recordValue, String value) {
        if (recordValue instanceof Number) {
            try {
                return Double.parseDouble(recordValue.toString()) == Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return recordValue.toString().equals(value);
        }
    }

    private static boolean isGreaterThan(Object recordValue, String value) {
        if (recordValue instanceof Number) {
            try {
                return Double.parseDouble(recordValue.toString()) > Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private static boolean isLessThan(Object recordValue, String value) {
        if (recordValue instanceof Number) {
            try {
                return Double.parseDouble(recordValue.toString()) < Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private static boolean isLike(String recordValue, String pattern) {
        // Convert SQL syntax to Java regex
        String regex = pattern.replace("%", ".*").replace("_", ".");
        return recordValue.matches(regex);
    }
}
