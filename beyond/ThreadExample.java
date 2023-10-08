package beyond;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                synchronized (new Object()) {

                }
                if(finalI == 2) {
                    throw new OutOfMemoryError();
                    // throw new RuntimeException("Exception occur");
                } else {
                    System.out.println(finalI + " normal execute");
                }
            });
            threads[i].start();
        }
    }
}
