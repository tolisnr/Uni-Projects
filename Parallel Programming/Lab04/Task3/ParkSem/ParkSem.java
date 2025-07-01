import java.util.concurrent.Semaphore;

public class ParkSem {
    private int capacity;
    private int spaces;
    private int waitscale;
    private int inscale;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;

    public ParkSem(int c) {
        capacity = c;
        spaces = capacity;
        waitscale = 10;
        inscale = 5;
        mutex = new Semaphore(1);
        empty = new Semaphore(capacity);
        full = new Semaphore(0);
    }

    void arrive() {
        try {
            empty.acquire();
        } catch (InterruptedException e) {
        }
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " arrival");
        System.out.println(Thread.currentThread().getName() + "     parking");
        spaces--;
        System.out.println("free " + spaces);
        mutex.release();
        full.release();
    }

    void depart() {
        try {
            full.acquire();
        } catch (InterruptedException e) {
        }
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + "         departure");
        spaces++;
        System.out.println("free " + spaces);
        mutex.release();
        empty.release();
    }

    void park() {
        try {
            Thread.sleep((int) (Math.random() * inscale));
        } catch (InterruptedException e) {
        }
    }
}
