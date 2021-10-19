/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */


public class Philosopher extends DiningServerImpl implements Runnable
{
	//Your code here
	public int number;
	
	Philosopher( int id )
	{
		number = id;
	}
	
	@Override
	public void run() {
		//take forks
		try {
			System.out.println("Philosopher " + number + " will try to get forks soon");
			Thread.sleep((long) waitRandomTime());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		takeForks(number);
		
		//eating
		try {
			System.out.println("Philosopher " + number + " is eating. Be patient!!");
			Thread.sleep((long) waitRandomTime());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//return forks
		returnForks(number);
		
		System.out.println("Philosopher " + number + " is done");
	}
 
}
