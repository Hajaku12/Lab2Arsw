package edu.eci.arsw.primefinder;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class PrimeFinderThread extends Thread{

	
	int a, b;
	private final Object pauselock;
	
	private List<Integer> primeNumbers=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b, Object pauselock) {
		super();
		this.a = a;
		this.b = b;
		this.pauselock = pauselock;
	}

	public void run(){
		long startTime = System.currentTimeMillis();

		for (int i=a;i<=b;i++){
			if (System.currentTimeMillis() - startTime  >= 5000 ){
				pauseExecution();
				startTime = System.currentTimeMillis();
			}else if (isPrime(i)) {
				primeNumbers.add(i);
				System.out.println(i);
			}
		}
	}

	private void pauseExecution() {
		synchronized (pauselock){
			try {
				pauselock.wait();
			} catch (InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimeNumbers() {
		return primeNumbers;
	}
	
	
	
	
}
