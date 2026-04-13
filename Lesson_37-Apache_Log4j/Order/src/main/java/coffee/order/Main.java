
package coffee.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    
    public static void main(String[] args) {
        
        try{
            logger.info("Main function has started");
            CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard(logger);
            logger.info("Object \"CoffeeOrderBoard\" has been created");
            
            logger.info("Start adding orders");
            System.out.println(coffeeOrderBoard.add(new Order(0, "Ivan")));
            System.out.println(coffeeOrderBoard.add(new Order(0, "Sergey")));
            System.out.println(coffeeOrderBoard.add(new Order(0, "Mike")));
            System.out.println(coffeeOrderBoard.add(new Order(0, "Andrey")));
            logger.info("Adding orders has been finished");

            logger.info("Delivering first order in queue");
            System.out.println(coffeeOrderBoard.deliver());
            logger.info("Delivering order with id = 3");
            System.out.println(coffeeOrderBoard.deliver(3));
            logger.info("Second trying to deliver order with id = 3");
            System.out.println(coffeeOrderBoard.deliver(3));

            logger.info("Showing rest of orders");
            coffeeOrderBoard.draw();
            
            logger.info("Main function has ended");
            
            throw new RuntimeException("Testing exception logging");
        } catch (Exception ex){
            logger.error("Unexpected error!", ex);
        }
        
        
        
        
    }
    
}
