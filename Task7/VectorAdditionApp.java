import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VectorAdditionApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VectorProcessor vectorProcessor = new VectorProcessor();
        List<List<Integer>> vectors = new ArrayList<>();

        try {
            while (true) {
                System.out.println("Enter vectors (blank line to end):");

                vectors.clear();
                while (true) {
                    String input = scanner.nextLine();
                    if (input.isBlank()) break;

                    List<Integer> vector = vectorProcessor.parseVector(input);
                    vectors.add(vector);
                }

                List<Integer> result = vectorProcessor.addVectors(vectors);
                System.out.println("Result of vector addition: " + result);
                break;
            }
        } catch (DifferentVectorsLengthsException e) {
            System.out.print("Enter a custom error message (or press Enter to use default): ");
            String customMessage = scanner.nextLine();

            if (!customMessage.trim().isEmpty()) {
                System.out.println(customMessage);
            } else {
                System.out.println(e.getMessage());
            }
        } finally {
            scanner.close();
        }
    }
}
