import java.util.Arrays;

public class ValueCalculator {

    private final double[] numbers;
    
    public ValueCalculator(){
        this.numbers = new double[1000000];
    }
    
    public ValueCalculator(int size){
        if (size > 1000000) this.numbers = new double[size];
        else this.numbers = new double[1000000];
    }
    
    public void doCalc(){
        long startTime = System.currentTimeMillis();
        
        Arrays.fill(numbers, 1);
        double[] firstPart = new double[this.numbers.length/2];
        double[] secondPart = new double[this.numbers.length-firstPart.length];
        System.arraycopy(this.numbers, 0, firstPart, 0, firstPart.length);
        System.arraycopy(this.numbers, firstPart.length, secondPart, 0, secondPart.length);
        
        new Thread(() -> {
            for (int i = 0; i < firstPart.length; i++){
                firstPart[i] = firstPart[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2);
            }
        }).start();
        new Thread(() -> {
            for (int i = firstPart.length; i < this.numbers.length; i++){
                secondPart[i - firstPart.length] = secondPart[i - firstPart.length] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2);
            }
        }).start();
        
        System.arraycopy(firstPart, 0, this.numbers, 0, firstPart.length);
        System.arraycopy(secondPart, 0, this.numbers, firstPart.length, secondPart.length);
        System.out.println("Done! Time needed: " + (System.currentTimeMillis()-startTime) + " ms");
    }
    
}
