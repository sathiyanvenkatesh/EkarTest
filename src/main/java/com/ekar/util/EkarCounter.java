/**
 * 
 */
package com.ekar.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sathiyan
 *
 */
public class EkarCounter {
	 private static volatile EkarCounter instance = new EkarCounter();
     
	 static AtomicInteger counter = new AtomicInteger(50); // a global counter
	     
	    // This private constructor is to prevent this object get instantiated more than once.
	    private EkarCounter(){}
	     
	    public static EkarCounter getInstance()
	    {
	    	 if (instance == null) { 
	 	    	// Single Checked 
	 	    	synchronized (EkarCounter.class)
	 	    	{ if (instance == null) {
	 	    		// Double checked 
	 	    		instance = new EkarCounter(); } } } return instance;
	    }
	     
	    @SuppressWarnings("static-access")
		public int increment()
	    {
	        return this.counter.getAndIncrement();
	        
	    }
	     
	    @SuppressWarnings("static-access")
		public int decrement()
	    {
	        return this.counter.getAndDecrement();
	    }
	   /* public static void main(String args[])
	    {
	    	EkarCounter s = EkarCounter.getInstance();      
	        s.increment();s.increment();s.increment();
	        System.out.println(s.counter); //3
	         
	        EkarCounter s2 = EkarCounter.getInstance();
	        s2.decrement();
	        System.out.println(s2.counter); //3 also, because s2 is the same instance as s, just two different variable pointing to the same instance
	         
	        String txt;
	        txt = (s.equals(s2)) ? "same":"not same";
	        System.out.println(txt); //same
	    }*/

}
