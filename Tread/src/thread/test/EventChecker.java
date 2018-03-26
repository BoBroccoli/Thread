package thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventChecker implements Runnable {
	private IntGenerator generator;
	private final int id;

	public EventChecker(IntGenerator generator, int ident) {
		this.generator = generator;
		id = ident;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even!" + Thread.currentThread());
				generator.cancel();
			}
		}
	}

	public static void test(IntGenerator gp, int count) {
		System.out.println("press C-C to exit");
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i =0; i<count ; i++) {
			executorService.execute(new EventChecker(gp, i));
		}
		executorService.shutdown();
	}
	public static void test(IntGenerator gp) {
		test(gp, 10);
	}
}
