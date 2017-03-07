package Dequeue;

public class EmptyDequeueException extends RuntimeException
{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public EmptyDequeueException(){
	 this (null);
 } // end default constructor
 
 public EmptyDequeueException(String message){
	 super (message);
 } // end constructor
} // end EmptyDequeueException
