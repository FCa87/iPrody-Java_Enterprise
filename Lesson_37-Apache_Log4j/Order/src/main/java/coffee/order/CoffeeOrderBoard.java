
package coffee.order;

import java.util.LinkedList;
import org.apache.logging.log4j.Logger;


public class CoffeeOrderBoard {
    
    private final Logger logger;
    
    private final LinkedList<Order> orders = new LinkedList<>();

    public CoffeeOrderBoard(Logger logger) {
        this.logger = logger;
    }
    
    public Order add(Order order){
        logger.info("Finction \"add\" started");
        Order addingOrder = new Order(1, order.getName());
        if (!orders.isEmpty()){
            addingOrder.setOrderID(orders.getLast().getOrderID() + 1);
        }
        orders.add(addingOrder);
        logger.info("Finction \"add\" successfully finished");
        return new Order(addingOrder.getOrderID(), addingOrder.getName());
    }
    
    public Order deliver(){
        logger.info("Finction \"deliver()\" started");
        Order result = orders.poll();
        if (result != null){
            logger.info("Finction \"deliver()\" successfully finished");
        } else{
            logger.info("There is no order in queue!");
            System.out.println("There is no order in queue!");
        }
        return orders.poll();
    }
    
    public Order deliver(int id){
        logger.info("Finction \"deliver(id)\" started");
        Order result = null;
        int index = 0;
        for (Order order : orders){
            if (order.getOrderID() == id){
                result = new Order(order.getOrderID(), order.getName());
                break;
            }
            index++;
        }
        if (result != null){
            orders.remove(index);
            logger.info("Finction \"deliver(id)\" successfully finished");
        } else{
            logger.warn("Order with id = " + id + " hasn't been found!");
            System.out.println("Order with id = " + id + " hasn't been found! Please enter another one");
        }
        return result;
    }
    
    public void draw(){
        logger.info("Finction \"draw()\" started");
        orders.forEach(System.out::println);
        logger.info("Finction \"draw()\" successfully finished");
    }
    
}
