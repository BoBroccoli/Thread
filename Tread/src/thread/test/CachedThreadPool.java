package thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
	public static void main(String[] args) {
		//ExecutorService executorService = Executors.newCachedThreadPool();
		//serilize the tasks. 
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for(int i = 0; i<5; i++) {
			executorService.execute(new LiftOff());
		}
		executorService.shutdown();
	}
}
