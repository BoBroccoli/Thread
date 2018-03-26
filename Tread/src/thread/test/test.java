package thread.test;

public class test implements Runnable{
	private static int Number = 1;
	@Override
	public void run() {
		Number = (int) (Math.random()*100);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Number);
	}
	public static void main(String[] args) {
		Thread thread1 = new Thread(new test());
		Thread thread2 = new Thread(new test());
		thread1.start();
		thread2.start();
		
		Number = 10;
		System.out.println(Number);
	}
}
