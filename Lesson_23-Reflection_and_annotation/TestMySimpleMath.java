
public class TestMySimpleMath {
    
    public TestMySimpleMath() {
    }
    
    @BeforeSuite
    private void testPrepare() {
        System.out.println("Preparing testing");
    }

    @Test(order = 5)
    private void Test1() {
        if (MySimpleMath.sum(5, 4) == 9) System.out.println("SumTwoGivenIntValues passed");   
        else System.out.println("SumTwoGivenIntValues failed");
    }

    @Test(order = 10)
    private void Test2() {
        if (MySimpleMath.mul(8, 9) == 72) System.out.println("MulTwoGivenIntValues passed");   
        else System.out.println("MulTwoGivenIntValues failed");
    }
    
    @Test(order = 10)
    private void Test3() {
        if (MySimpleMath.sub(500, 85) == 415) System.out.println("SubTwoGivenIntValues passed");   
        else System.out.println("SubTwoGivenIntValues failed");
    }

    @Test
    private void Test4() {
        if (MySimpleMath.div(60, 4) == 15) System.out.println("DivTwoGivenIntValues passed");   
        else System.out.println("DivTwoGivenIntValues failed");
    }
    
    @AfterSuite
    private void testComplete() {
        System.out.println("Summuring results");
    }

    private void doSomething(){
        System.out.println("Doing something");
    }
    
}
