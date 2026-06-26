public class Buffer
{
	private int content;
	private boolean bufferEmpty = true;
	private boolean bufferFull = false;
	private int counter = 0;

	// Constructor
	public Buffer() {
		content = 0;
	}

	// Put an item into buffer
	public synchronized void put(int data)
	{
		while (bufferFull) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		content = data;
		counter++;
		System.out.println("Prod: Item " + data + ". Count = " + counter);
		bufferEmpty = false;
		if (counter==1)
		{
			bufferFull = true;
			System.out.println("The buffer is full");
			notifyAll();
		}
	}

	// Get an item from bufffer
	public synchronized int get()
	{
		while (bufferEmpty) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		int data = content;
		counter--;
		System.out.println("Cons: Item " + data + ". Count = " + counter);
		bufferFull = false;
		if (counter==0) 
		{
			bufferEmpty = true;
			System.out.println("The buffer is empty");
			notifyAll();
		}
		return data;
	}
}

	
			
	
