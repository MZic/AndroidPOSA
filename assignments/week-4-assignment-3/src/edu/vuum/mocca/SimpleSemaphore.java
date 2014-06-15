package edu.vuum.mocca;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @class SimpleSemaphore
 * 
 * @brief This class provides a simple counting semaphore implementation using
 *        Java a ReentrantLock and a ConditionObject (which is accessed via a
 *        Condition). It must implement both "Fair" and "NonFair" semaphore
 *        semantics, just liked Java Semaphores.
 */
public class SimpleSemaphore {
    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
	private Lock lock;

    /**
     * Define a Condition that waits while the number of permits is 0.
     */
    // TODO - you fill in here
	private Condition cWait;

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here. Make sure that this data member will
    // ensure its values aren't cached by multiple Threads..
	private volatile int permits;
	
    public SimpleSemaphore(int permits, boolean fair) {
        // TODO - you fill in here to initialize the SimpleSemaphore,
        // making sure to allow both fair and non-fair Semaphore
        // semantics.
    	this.permits = permits;
    	lock = new ReentrantLock(fair);
    	cWait = lock.newCondition();    	
    }

    /**
     * Acquire one permit from the semaphore in a manner that can be
     * interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here.
	  	  lock.lock();
	
	      try {
	          while (availablePermits() == 0) {
	        	  cWait.await();
	          }
	          permits = permits - 1;
	
	      } finally {
	          lock.unlock();
	      }  
    }

    /**
     * Acquire one permit from the semaphore in a manner that cannot be
     * interrupted.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here.
	  	  lock.lock();
	  	
	      try {
	          while (availablePermits() == 0) {
	        	  cWait.awaitUninterruptibly();
	          }
	          permits = permits - 1;
	          
	      } finally {
	          lock.unlock();
      }      	
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here.
	  lock.lock();

      try {
          permits = permits + 1;
          cWait.signal();
      } finally {
          lock.unlock();
      } 
    }

    /**
     * Return the number of permits available.
     */
    public int availablePermits() {
        // TODO - you fill in here to return the correct result
	  	int result;
    	lock.lock();
	
	    try {
	  	  result = permits;
	    } finally {
	      lock.unlock();
	    } 
        return result;
    }
}
