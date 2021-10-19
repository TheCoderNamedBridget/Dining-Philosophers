
/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * @author bridg
 *ALGO: make an arraylist of locks for each chopstick
 */
public class DiningServerImpl implements DiningServer {
	static ReentrantLock lock1 = new ReentrantLock();
	static ReentrantLock lock2 = new ReentrantLock();
	static ReentrantLock lock3 = new ReentrantLock();
	static ReentrantLock lock4 = new ReentrantLock();
	static ReentrantLock lock5 = new ReentrantLock();

	static List<ReentrantLock> locks = new ArrayList<ReentrantLock>();
	

	public static void initialize() {

		locks.add(lock1);
		locks.add(lock2);
		locks.add(lock3);
		locks.add(lock4);
		locks.add(lock5);

	}

	@Override
	public void takeForks(int philNumber) {
		System.out.println("entered get forks " + philNumber);
		while ( locks.get(philNumber % 5).isLocked() || locks.get((philNumber + 1)% 5).isLocked() ){//forks.get(philNumber % 5) != 1 || forks.get((philNumber + 1) % 5) != 1) {
			// do nothing
			// System.out.println("waiting thread = " + philNumber);
			try {
				
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		locks.get((philNumber % 5)).lock();
		locks.get(((philNumber + 1) % 5)).lock();
		System.out.println("philosopher " + (philNumber) + " successfully grabbed forks! " + ((philNumber + 1) % 5)
				+ " and " + (philNumber % 5));

	}

	@Override
	public void returnForks(int philNumber) {
		System.out.println("entered returnForks" + philNumber);
		// no need to for synchronizing here
//		synchronized (forks) {
//		
//			if (philNumber == 5) {

				locks.get((philNumber % 5)).unlock();
				locks.get(((philNumber + 1) % 5)).unlock();
//			} else {
//				forks.set(philNumber - 1, 1);
//				forks.set(philNumber, 1);
//				locks.get(philNumber - 1).unlock();
//				locks.get(philNumber).unlock();
//
//			}
//		}
		System.out.println("philosopher " + philNumber + " successfully put down forks!");

	}

	public double waitRandomTime() {
		return 3000 + Math.random() * 2000;
	}
}
