public class QuadraticEquationSolver {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Error: Please provide exactly 3 integer arguments for coefficients a, b, and c.");
            return;
        }

        try {   
            // Parse command-line arguments to integers
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);

            if (a == 0) {
                System.out.println("Error: Coefficient 'a' must not be zero. This is not a quadratic equation.");
                return;
            }

            int discriminant = b * b - 4 * a * c;

            if (discriminant > 0) {
                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                System.out.println("The equation has two distinct real roots: " + root1 + " and " + root2);
            } else if (discriminant == 0) {
                double root = -b / (2.0 * a);
                System.out.println("The equation has one real repeated root: " + root);
            } else {
                System.out.println("The equation has no real roots.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please ensure all arguments are valid integers.");
        }
    }
}
