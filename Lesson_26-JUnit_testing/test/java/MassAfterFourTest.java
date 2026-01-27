
import my.HomeWork;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MassAfterFourTest {
    
    public MassAfterFourTest() {
    }

    @Test
    public void nullTest(){
        int[] result = HomeWork.massAfterFour(null);
        
        Assertions.assertEquals(null, result);
    }
    
    @Test
    public void massFourInMiddle(){
        int[] input = {0, 1, 5, 8, 4, 7, 8, 5};
        int[] correctOutput = {7, 8, 5};
        int[] result = HomeWork.massAfterFour(input);
        
        Assertions.assertArrayEquals(correctOutput, result);
    }
    
    @Test
    public void noFour(){
        final int[] input = {0, 1, 5, 8, 7, 8, 5};
        
        RuntimeException exception = Assertions.assertThrowsExactly(RuntimeException.class, () -> {HomeWork.massAfterFour(input);});
        Assertions.assertTrue(exception.getMessage().endsWith("There is no \"4\" in the input mass!"));
    }
    
    @Test
    public void fourInTheEnd(){
        int[] input = {0, 1, 5, 8, 4, 7, 8, 5, 4};
        int[] correctOutput = new int[0];
        int[] result = HomeWork.massAfterFour(input);
        
        Assertions.assertArrayEquals(correctOutput, result);
    
    }
}
