import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 *
 */

public class DiningPhilosophers extends DiningServerImpl {

	public static void main(String args[]) {
		initializeForks();
		// Create Philosophers and run them here
		Philosopher p1 = new Philosopher(1);
		Philosopher p2 = new Philosopher(2);
		Philosopher p3 = new Philosopher(3);
		Philosopher p4 = new Philosopher(4);
		Philosopher p5 = new Philosopher(5);

		ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>() {
			{
				add(p1);
				add(p2);
				add(p3);
				add(p4);
				add(p5);
			}
		};

		ExecutorService diningTable = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 5; i++) {
			diningTable.execute(philosophers.get(i));
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		diningTable.shutdown();

	}
}
