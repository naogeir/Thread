import java.util.concurrent.Semaphore;

/**
 * @author noageir
 * Date 2019-03-26 17:17
 * Project: Thread
 * Package: PACKAGE_NAME
 */
public class NewThread {
    private static final int N = 5;
    private static int RESULT = 0;

    public static void main(String[] args) {
        try {
            Thread[] threads = new Thread[N];
            final Semaphore[] syncObjects = new Semaphore[N];
            for (int i = 0; i < N; i++) {
                syncObjects[i] = new Semaphore(1);
                if (i != N - 1) {
                    syncObjects[i].acquire();
                }
            }

            for (int j = 0; j < N; j++) {
                final Semaphore lastSemaphore = j == 0 ? syncObjects[N - 1] : syncObjects[j - 1];
                final Semaphore curSemaphore = syncObjects[j];
                final int index = j;
                threads[j] = new Thread(() -> {
                    try {
                        while (true) {
                            lastSemaphore.acquire();
                            System.out.println("线程" + index + ":" + RESULT++);
                            if (RESULT > 100) {
                                System.exit(0);
                            }
                            curSemaphore.release();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                threads[j].start();
            }
        } catch (InterruptedException e) {
            System.out.println("Error...");
        }
    }
}
