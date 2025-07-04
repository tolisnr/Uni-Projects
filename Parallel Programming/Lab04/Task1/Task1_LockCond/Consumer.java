public class Consumer extends Thread {
	private Buffer buff;
	private int scale;

	// Constructor
	public Consumer(Buffer b, int s) {
		this.buff = b;
		this.scale = s;
	}

	// Producer runs for reps times with random(scale) intervals
	public void run() {
		while (true) {
			try {
				sleep((int)(Math.random()*scale));
			} catch (InterruptedException e) { }
			int value = buff.get();
		}
	}
}
