public class Polynomial{
    double field [];

    public Polynomial(){
        field = new double[]{0};
    }
    public Polynomial(double[] input){
        field = new double[input.length];
        for(int i = 0; i < input.length; i++) {
        	field[i] = input[i];
        }
    }
    public Polynomial add(Polynomial other){
    	int max_length = Math.max(field.length, other.field.length);
        double[] new_array = new double[max_length];
        for(int i = 0; i < max_length; i++){
        	if(i < field.length && i < other.field.length) {
            new_array[i] = field[i]+other.field[i];
        	}
        	else if(i < field.length) {
        		new_array[i] = field[i];
        	}
        	else if(i < other.field.length) {
        		new_array[i] = other.field[i];
        	}
        }
       return new Polynomial(new_array);
    }
    public double evaluate(double x){
        double result = 0;
        for(int i = 0; i < field.length; i++){
            result = result + field[i]*Math.pow(x, i);
        }
        return result;
    }
    public boolean hasRoot(double x) {
    	return evaluate(x) == 0;
    }
}