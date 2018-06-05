package per.tom;

public class Timer extends Thread {
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
	
}
