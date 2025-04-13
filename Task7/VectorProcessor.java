import java.util.ArrayList;
import java.util.List;

public class VectorProcessor {
    public List<Integer> addVectors(List<List<Integer>> vectors) throws DifferentVectorsLengthsException {
        if (vectors.isEmpty()) return new ArrayList<>();

        int expectedLength = vectors.get(0).size();
        List<Integer> vectorLengths = new ArrayList<>();

        for (List<Integer> vector : vectors) {
            vectorLengths.add(vector.size());
            if (vector.size() != expectedLength) {
                throw new DifferentVectorsLengthsException(
                    vectorLengths,
                    "Vector lengths do not match. Mismatched lengths: " + vectorLengths
                );
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < expectedLength; i++) {
            result.add(0); // initialize empty list
        }

        for (List<Integer> vector : vectors) {
            for (int i = 0; i < expectedLength; i++) {
                result.set(i, result.get(i) + vector.get(i));
            }
        }

        return result;
    }

    public List<Integer> parseVector(String input) {
        List<Integer> vector = new ArrayList<>();
        for (String element : input.split(",")) {
            try {
                int number = Integer.parseInt(element.trim());
                vector.add(number);
            } catch (NumberFormatException e) {
                // Ignore non-numeric elements
            }
        }
        return vector;
    }
}
