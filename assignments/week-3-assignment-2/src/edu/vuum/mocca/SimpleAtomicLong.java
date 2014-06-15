// Import the necessary Java synchronization and scheduling classes.

package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

/**
 * @class SimpleAtomicLong
 *
 * @brief This class implements a subset of the
 *        java.util.concurrent.atomic.SimpleAtomicLong class using a
 *        ReentrantReadWriteLock to illustrate how they work.
 */
class SimpleAtomicLong
{
    /**
     * The value that's manipulated atomically via the methods.
     */
    private long mValue;


    /**
     * The ReentrantReadWriteLock used to serialize access to mValue.
     */
    // TODO - replace the null with the appropriate initialization:
    private ReentrantReadWriteLock mRWLock = new ReentrantReadWriteLock();

    /**
     * Creates a new SimpleAtomicLong with the given initial value.
     */
    public SimpleAtomicLong(long initialValue) {
        // TODO - you fill in here
    	this.mValue = initialValue;    	
    }

    /**
     * @brief Gets the current value
     * 
     * @returns The current value
     */
    public long get() {
        // TODO - you fill in here
        long value;
    	
        mRWLock.readLock().lock();
        try {
        	value = mValue;
        } finally {
        	mRWLock.readLock().unlock();
        }
        
        return value;    	
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the updated value
     */
    public long decrementAndGet() {
        // TODO - you fill in here
        long value = 0;
        
        mRWLock.writeLock().lock();
        try{
         mValue = mValue-1;
        }finally{
        	mRWLock.writeLock().unlock();
        }

        value = get();
        return value; 	
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the previous value
     */
    public long getAndIncrement() {
        // TODO - you fill in here
        long value = 0;

        value = get();
        mRWLock.writeLock().lock();
        try{
          mValue = mValue+1;
        }finally{
        	mRWLock.writeLock().unlock();
        }
        
        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the previous value
     */
    public long getAndDecrement() {
        // TODO - you fill in here
        long value = 0;

        value = get();
        mRWLock.writeLock().lock();
        try{
          mValue = mValue-1;
        }finally{
        	mRWLock.writeLock().unlock();
        }
        
        return value;    	
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the updated value
     */
    public long incrementAndGet() {
        // TODO - you fill in here
        long value = 0;

        mRWLock.writeLock().lock();
        try{
          mValue = mValue+1;
        }finally{
        	mRWLock.writeLock().unlock();
        }
        
        value = get();       
        return value;   	
    }
}

