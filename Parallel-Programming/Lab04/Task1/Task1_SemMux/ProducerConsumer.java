public class ProducerConsumer
{
	public static void main(String[] args)
	{
		// int bufferSize = 1;
		int noIterations = 20;
		int producerDelay = 1;
		int consumerDelay = 100;
		Producer prod;
		Consumer cons;
		

		// Bounded Buffer
		Buffer buff = new Buffer();
		// Ο buffer αρχικοποιείται στο 0 με ένα μοναδικό στοιχείο
		
		// Single Producer
		prod = new Producer(buff, noIterations, producerDelay);
		prod.start();

		// Single Consumer
		cons = new Consumer(buff, consumerDelay);
		cons.start();
	}
}
