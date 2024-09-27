import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));

        double [] c1 = {6, 5};
        int [] e1 = {0, 3};
        Polynomial p1 = new Polynomial(c1, e1);

        double [] c2 = {-2, -9};
        int [] e2 = {1, 4};
        Polynomial p2 = new Polynomial(c2, e2);

        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));

        if (s.hasRoot(1)) {
            System.out.println("1 is a root of s");
        } else {
            System.out.println("1 is not a root of s");
        }

        Polynomial poly1 = new Polynomial(new double[]{2, 3, 4}, new int[]{2, 0, 1}); // 2x^2 + 3 + 4x
        Polynomial poly2 = new Polynomial(new double[]{1, 5, 1}, new int[]{2, 1, 0}); // x^2 + 5x + 1
        Polynomial result = poly1.multiply(poly2);
		result.printPolynomial();
        poly1.saveToFile("output_polynomial.txt");
		poly1.printPolynomial();

        try {
            File polynomialFile = new File("output_polynomial.txt"); 
            Polynomial polyFromFile = new Polynomial(polynomialFile);
            polyFromFile.printPolynomial();  
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Make sure 'polynomial.txt' exists.");
        }
    }
}
