import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ParkLockCond{

	private int capacity;
	private int spaces;
	private int waitscale;
	private int inscale;
    private Lock lock = new ReentrantLock();
	private Condition full = lock.newCondition();
	private Condition empty = lock.newCondition();
    
    public ParkLockCond(int c) {
       capacity = c;
       spaces = capacity;
       waitscale = 10;
       inscale = 5;
    }
       
	void arrive () {
		//Car arrival with radom delay
        lock.lock();
		try {
		//    Thread.sleep((int)(Math.random()*waitscale));
            while(spaces == 0) {
                System.out.println(Thread.currentThread().getName() + " waiting");
                // Wait until a space is available
                try {
                    full.await();
                } catch (InterruptedException e) { }
            }
            System.out.println(Thread.currentThread().getName() + " arrival");
            // Car entering
            System.out.println(Thread.currentThread().getName() + "     parking");
            // Decrement capacity
            spaces--;
            System.out.println("free " + spaces);
            empty.signal(); // Notify waiting cars that a space is available
		}
        finally {
            lock.unlock();
        }
	}
        
    void depart () {
        lock.lock();
        try {
            while (spaces == capacity) {
                System.out.println(Thread.currentThread().getName() + " waiting");
                try {
                    empty.await(); // Wait until a car departs
                } catch (InterruptedException e) {
                }
            }
            //Car departure
            System.out.println(Thread.currentThread().getName()+"         departure");
            //Increment capacity
            spaces++;
            System.out.println("free "+ spaces);
            full.signal(); // Notify waiting cars that a space is available
        } finally {
            lock.unlock();
        }
    }            
           
    void park() {    
        try {
                Thread.sleep((int)(Math.random()*inscale));
            } catch (InterruptedException e) { }
    }
}
