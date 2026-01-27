
package iprody.mavenexploring.Utils;

public class ExtendedCalculator {
    
    public static long summation(int a, int b){
        return (long)a + b;
    }
    
    public static long subtraction(int a, int b){
        return (long)a - b;
    }
    
    public static long multiply(int a, int b){
        return (long)a * b;
    }
    
    public static double division(int a, int b){
        if (b == 0){
            throw new RuntimeException("You can't devide by zero!");
        }
        return (double)a / b;
    }
    
    public static long factorial(int number){
        if (number < 0){
            throw new RuntimeException("Number must be positive!");
        }
        if (number > 20){
            throw new RuntimeException("Facterial is too big!");
        }
        long result = 1;
        for (int i = 2; i <= number; i++){
            result *= i;
        }
        return result;
    }
    
    public static double naturalLog(double number){
        if (number <= 0){
            throw new RuntimeException("Number must be greater then zero!");
        }
        return Math.log(number);
    }
    
}
