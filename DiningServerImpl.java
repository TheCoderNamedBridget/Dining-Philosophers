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

public class DiningServerImpl implements DiningServer
{
	static List<Integer> forks = Collections.synchronizedList(new ArrayList<Integer>());
	 

	public static void initializeForks() 
	{
	     forks.add(1);
	     forks.add(1);
	     forks.add(1);
	     forks.add(1);
	     forks.add(1);

	}

	@Override
	public void takeForks(int philNumber) {
		
		System.out.println("entered get forks " + philNumber);
		//add synchronized piece here
//		synchronized(forks)
//		{
//			if ( philNumber == 5)
//			{
				System.out.println("entered takeForks index = " + (philNumber%5) + " " + ((philNumber+1)%5));
				System.out.println("entered takeForks" + forks.get(philNumber%5) + " " + forks.get((philNumber+1)%5));
				while ( forks.get(philNumber%5) != 1 || forks.get((philNumber+1)%5) != 1 )
				{
					//do nothing
//					System.out.println("waiting thread = " + philNumber);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				forks.set((philNumber%5), 0);
				forks.set(((philNumber+1)%5), 0);  
				System.out.println("entered takeForks" + forks.get((philNumber%5)) + " " + forks.get((philNumber+1)%5));
				System.out.println("philosopher " + (philNumber) + " successfully grabbed forks! " + ((philNumber+1)%5) + " and " + (philNumber%5) );

	}

	@Override
	public void returnForks(int philNumber) {
		System.out.println("entered returnForks" + philNumber);
		//no need to for synchronizing here
		synchronized(forks)
		{
		if ( philNumber == 5)
		{
			forks.set(philNumber-1, 1);
			forks.set(0, 1);
		}
		else
		{
			forks.set(philNumber-1, 1);
			forks.set(philNumber, 1);
		}
		}
		System.out.println("philosopher " + philNumber + " successfully put down forks!");

		
	}  

	public double waitRandomTime()
	{
		return 3000 + Math.random()*2000;
	}
}
