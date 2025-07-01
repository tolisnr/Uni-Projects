
public class ParkMon{

	private int capacity;
	private int spaces;
	private int waitscale;
	private int inscale;
    private boolean full = false;
    private boolean empty = true;
    
    public ParkMon(int c) {
       capacity = c;
       spaces = capacity;
       waitscale = 10;
       inscale = 5;
    }
       
	synchronized void arrive () {
		//Car arrival with radom delay
		while (full) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
		System.out.println(Thread.currentThread().getName()+" arrival");
		//Car entering
		System.out.println(Thread.currentThread().getName()+"     parking");
		//Decrement capacity
		spaces--;
		System.out.println("free "+ spaces);
        empty = false;
        //If the parking is full
        if (spaces == 0) {
            full = true;
            System.out.println("The parking is full");
        }
        notifyAll();
	}
        
    synchronized void depart () {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        //Car departure
        System.out.println(Thread.currentThread().getName()+"         departure");
        //Increment capacity
        spaces++;
        System.out.println("free "+ spaces);

        full = false;
        if (spaces == capacity) {
            empty = true;
            System.out.println("The parking is empty");
        }
        notifyAll();
    }            
           
    synchronized void park() {    
        try {
                Thread.sleep((int)(Math.random()*inscale));
            } catch (InterruptedException e) { }
    }
}
