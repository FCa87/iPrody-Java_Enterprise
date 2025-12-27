import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class PetrolStation {
    
    private double fuelQuantity;
    private final Random random = new Random();
    private final Lock lock = new ReentrantLock();
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);
    
    public PetrolStation(){
        this.fuelQuantity = 0.0d;
    }
    
    public PetrolStation(double quantity){
        this.fuelQuantity = quantity;
    }
    
    public void PetrolStationShutDown(){
        executorService.shutdown();
    }
    
    public void addFuel(double quantity){
        try{
            lock.lock();
            this.fuelQuantity += quantity;
        }finally{
            lock.unlock();;
        }
    }
    
    public void doTank(double quantityNeeded){
        try{
            lock.lock();
            if (this.fuelQuantity >= quantityNeeded){
                this.fuelQuantity -= quantityNeeded;
                executorService.submit( () -> {
                    try {
                        Thread.sleep(random.nextInt(3000, 10001));
                    } catch (InterruptedException ex) {
                        throw new RuntimeException();
                    }
                });
            }else{
                System.out.println("Sorry! Only " + this.fuelQuantity + " of petrol left");
            }
        }finally{
            lock.unlock();
        }
    }
    
}
