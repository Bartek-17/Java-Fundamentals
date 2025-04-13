public class Substring {

    public static void main(String[] args) {
        // Step 1: Check if the number of arguments is correct
        if (args.length != 3) {
            System.out.println("Error: You must provide exactly 3 arguments: a string and two integers.");
            return;
        }

        String inputString = args[0];
        int a, b;

        // Step 2: Parse the integer arguments and handle possible exceptions
        try {
            a = Integer.parseInt(args[1]);
            b = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: The second and third arguments must be integers.");
            return;
        }

        // Step 3: Check if the indices are valid
        if (a < 0 || b < 0) {
            System.out.println("Error: Indices must be non-negative.");
            return;
        }

        if (a >= b) {
            System.out.println("Error: The first index (a) must be less than the second index (b).");
            return;
        }

        if (b > inputString.length()) {
            System.out.println("Error: The second index (b) must be less than or equal to the length of the string.");
            return;
        }

        // Step 4: Extract the substring and display it
        String substring = inputString.substring(a, b);
        System.out.println("Extracted substring: " + substring);

        // Step 5: Remove the substring and display the remaining string
        String modifiedString = inputString.substring(0, a) + inputString.substring(b);
        System.out.println("Original string without the extracted substring: " + modifiedString);
    }
}
