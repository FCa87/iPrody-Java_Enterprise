import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadSafeList<T> {
    
    private final ArrayList<T> arrayList = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public ThreadSafeList (){
    }
    
    public ThreadSafeList (List<T> c){
        arrayList.addAll(c);
    }
    
    public void add(T element){
        try{
            lock.writeLock().lock();
            this.arrayList.add(element);
        }catch(RuntimeException ex){
            System.out.println("Error adding!");
        }finally{
            lock.writeLock().unlock();
        } 
    }
    
    public void remove(T element){
        try{
            lock.writeLock().lock();
            this.arrayList.remove(element);
        }catch(RuntimeException ex){
            System.out.println("Error removing!");
        }finally{
            lock.writeLock().unlock();
        } 
    }
    
    public T get(int index){
        T t = null;
        try{
            lock.readLock().lock();
            t = this.arrayList.get(index);
        }catch(RuntimeException ex){
            System.out.println("Error searching: " + index + " index");
        }finally{
            lock.readLock().unlock();
            return t;
        }
    }
    
    public int size(){
        try{
            lock.readLock().lock();
            return this.arrayList.size();
        }finally{
            lock.readLock().unlock();
        }
    }
    
}
