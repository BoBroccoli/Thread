package thread.test;

public class MainThread {
	public static void main(String[] args) throws InterruptedException {
		// LiftOff launch = new LiftOff();
		// launch.run();
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new LiftOff());
			thread.start();
		}
		System.out.println("waiting for liftoff");
	}
}
