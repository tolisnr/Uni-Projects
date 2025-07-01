import java.util.concurrent.Semaphore;
public class Buffer
{
	private int content;
	private int counter = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore bufferFull = new Semaphore(0);
	private Semaphore bufferEmpty = new Semaphore(1); 

	// Constructor
	public Buffer() {
		content = 0;
	}

	// Put an item into buffer
	public void put(int data) {
		try {
			bufferEmpty.acquire();
		} catch (InterruptedException e) { }
		try {
			mutex.acquire();
		} catch (InterruptedException e) { }
		content = data;
		counter++;
		System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Count = " + counter);
		System.out.println("The buffer is full");
		mutex.release(); 
		bufferFull.release(); 
	}

	// Get an item from buffer
	public int get() {
		int data = 0;
		try {
			bufferFull.acquire();
		} catch (InterruptedException e) { }
		try {
			mutex.acquire();
		} catch (InterruptedException e) { }
		data = content;
		System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Count = " + (counter-1));
		counter--;	
		System.out.println("The buffer is empty");	
		mutex.release();		
		bufferEmpty.release();
		return data;
	}
}

	
			
	
