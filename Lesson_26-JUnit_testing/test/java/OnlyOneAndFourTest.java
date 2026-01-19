
import my.HomeWork;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OnlyOneAndFourTest {
    
    public OnlyOneAndFourTest() {
    }
    
    @Test
    public void rightTest(){
        int[] input = {1, 1, 1, 4, 4, 1, 4, 4};
        
        Assertions.assertTrue(HomeWork.onlyOneAndFour(input));
    }
    
    @Test
    public void oneTest(){
        int[] input = {1, 1, 1};
        
        Assertions.assertFalse(HomeWork.onlyOneAndFour(input));
    }
    
    @Test
    public void fourOnlyOneTest(){
        int[] input = {4};
        
        Assertions.assertFalse(HomeWork.onlyOneAndFour(input));
    }
    
    @Test
    public void otherDigitsTest(){
        int[] input = {1, 4, 4, 1, 1, 4, 3};
        
        Assertions.assertFalse(HomeWork.onlyOneAndFour(input));
    }
    
}
