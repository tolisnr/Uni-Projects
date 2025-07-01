public class ProducerConsumer
{
	public static void main(String[] args)
	{
		// int bufferSize = 1;
		int noIterations = 20;
		int producerDelay = 100;
		int consumerDelay = 1;
		Producer prod;
		Consumer cons;
		

		// Bounded Buffer
		Buffer buff = new Buffer();
		
		// Single Producer
		prod = new Producer(buff, noIterations, producerDelay);
		prod.start();

		// Single Consumer 
		cons = new Consumer(buff, consumerDelay);
		cons.start();
	}
}
