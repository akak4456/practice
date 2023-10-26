package e.thread.practice;

public class TimerThread extends Thread{
    public void run() {
        printCurrentTime();
    }
    public void printCurrentTime() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(System.currentTimeMillis());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
