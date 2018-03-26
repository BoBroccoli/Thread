package thread.test;

import java.security.AlgorithmConstraints;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();
	private int i;
	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("trylock() "+ captured);			
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
		
	}
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS): "+captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		final AttemptLocking attemptLocking = new AttemptLocking();
		attemptLocking.untimed();
		attemptLocking.timed();
		new Thread() {
			{setDaemon(true);}
			public void run() {
				attemptLocking.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		Thread.yield();
		attemptLocking.untimed();
		attemptLocking.timed();
	}
}
