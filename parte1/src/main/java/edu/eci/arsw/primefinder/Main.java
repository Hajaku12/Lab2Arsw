package edu.eci.arsw.primefinder;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//Realizado con un Thread
		//PrimeFinderThread pft=new PrimeFinderThread(0, 30000000);
		//pft.start();

		final Object pauseLock = new Object();
		PrimeFinderThread[] threads = new PrimeFinderThread[3];

		for (int i=0; i<threads.length; i++) {
			threads[i] = new PrimeFinderThread(i * 10000000, (i + 1) * 10000000 -1 ,pauseLock);
			threads[i].start();
		}

		boolean isRunning =true;

		Scanner scanner = new Scanner(System.in);

		while (isRunning){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			if (Thread.activeCount() == 2) {
				isRunning = false;
			}

			System.out.println("Presione Enter para continuar: ");
			scanner.nextLine();

			synchronized (pauseLock) {
				pauseLock.notifyAll();
			}

			}
		}
	}

