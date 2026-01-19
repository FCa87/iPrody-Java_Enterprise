
package my;

import java.util.Arrays;


public class HomeWork {
    
    public static void main(String[] args) {
        /*int[] input = {4, 1, 5, 8, 7, 89, 8, 5};
        System.out.println(Arrays.toString(Main.massAfterFour(input)));*/
    }
    
    public static int[] massAfterFour(int[] input){
        if (input == null) return null;
        int indexOfFour = -1;
        for (int i = input.length - 1; i >= 0; i--){
            if (input[i] == 4){
                indexOfFour = i;
                break;
            }
        }
        if (indexOfFour == -1){
            throw new RuntimeException("There is no \"4\" in the input mass!");
        }
        if (indexOfFour == input.length - 1){
            return new int[0];
        } else {
            return Arrays.copyOfRange(input, indexOfFour + 1, input.length);
        }
    }
    
    public static boolean onlyOneAndFour(int[] input){
        if (input == null) return false;
        boolean flagOne = false;
        boolean flagFour = false;
        for (int i = 0; i < input.length; i++){
            if (input[i] == 1){
                flagOne = true;
            } else if (input[i] == 4){
                flagFour = true;
            } else {
                return false;
            }
        }
        return flagOne && flagFour;
    }
    
}
