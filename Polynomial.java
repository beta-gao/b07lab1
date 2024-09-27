import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial{
    double coefficient [];
    int exponent [];

    public Polynomial(){
        coefficient = new double[]{0};
        exponent = new int[]{0};
    }
    public Polynomial(double[] input_co, int[] input_ex){
        coefficient = input_co.clone();
        exponent = input_ex.clone();
    }
    public Polynomial(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String all = scanner.nextLine();
        scanner.close();
        String[] terms = all.split("(?=[+-])");
        double[] new_coefficient = new double[terms.length];
        int[] new_exponent = new int[terms.length];
        for(int i = 0; i < terms.length; i++){
            if(terms[i].contains("x")){
                String[] term = terms[i].split("x");
                if(term[0].equals("+")||term[0].equals("")){
                    new_coefficient[i] = 1;
                }else if(term[0].equals("-")){
                    new_coefficient[i] = -1;
                }else{
                    new_coefficient[i] = Double.parseDouble(term[0]);
                }
                if (term.length > 1) {
                    new_exponent[i] = Integer.parseInt(term[1]);
                } else {
                    new_exponent[i] = 1;
            }
        }
            else{
                new_coefficient[i] = Double.parseDouble(terms[i]);
                new_exponent[i] = 0;
            }
        }
        coefficient = new_coefficient;
        exponent = new_exponent;
    }
    
    public void saveToFile(String name) {
        StringBuilder poly = new StringBuilder();

        for (int i = 0; i < coefficient.length; i++) {
            double coeff = coefficient[i];
            int exp = exponent[i];
            if (i > 0) {
                if (coeff > 0) {
                    poly.append("+");
                }
            }
            if (coeff == 1 && exp != 0) {
                poly.append("");
            } else if (coeff == -1 && exp != 0) {
                poly.append("-");
            } else {
                poly.append(coeff);
            }
            if (exp != 0) {
                poly.append("x");
                if (exp != 1) {
                    poly.append(exp);
                }
            }
        }
        try (FileWriter writer = new FileWriter(name)) {
            writer.write(poly.toString());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Polynomial add(Polynomial other){
        HashMap<Integer, Double> dictionary = new HashMap<>();
        for(int i = 0; i < coefficient.length; i++){
            dictionary.put(exponent[i], coefficient[i]);
        }
        for(int i = 0; i < other.coefficient.length; i++){
            dictionary.merge(other.exponent[i], other.coefficient[i], Double::sum);
        }
        int[] new_exponent = new int[dictionary.size()];
        double[] new_coefficient = new double[dictionary.size()];
        int index = 0;
        for (Map.Entry<Integer, Double> entry : dictionary.entrySet()) {
            new_exponent[index] = entry.getKey();
            new_coefficient[index] = entry.getValue();
            index++;
        }
        return new Polynomial(new_coefficient, new_exponent);
    }
    public double evaluate(double x){
        double result = 0;
        for(int i = 0; i < coefficient.length; i++){
            result = result + coefficient[i]*Math.pow(x, exponent[i]);
        }
        return result;
    }
    public boolean hasRoot(double x) {
    	return evaluate(x) == 0;
    }
    public Polynomial multiply(Polynomial other){
        HashMap<Integer, Double> dictionary = new HashMap<>();
        for(int i = 0; i < coefficient.length; i++){
            for(int k = 0; k < other.coefficient.length; k++){
                dictionary.merge(exponent[i]+other.exponent[k], coefficient[i]*other.coefficient[k], Double::sum);
            }
        }
        int[] new_exponent = new int[dictionary.size()];
        double[] new_coefficient = new double[dictionary.size()];
        int index = 0;
        for (Map.Entry<Integer, Double> entry : dictionary.entrySet()) {
            new_exponent[index] = entry.getKey();
            new_coefficient[index] = entry.getValue();
            index++;
        }
        return new Polynomial(new_coefficient, new_exponent);
    }
    public void printPolynomial() {
        for (int i = 0; i < coefficient.length; i++) {
            System.out.print(coefficient[i] + "x" + exponent[i] + " ");
        }
        System.out.println();
    }
}