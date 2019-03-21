import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: noageir
 * @Date: 2019/3/21 15:44
 * Project: Thread
 * Package: PACKAGE_NAME
 */
public class Producer extends Thread {
    private final Queue<Integer> queue;
    private int maxSize;

    Producer(String name, Queue<Integer> queue, int maxSize) {
        super(name);
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                try {
                    Thread.sleep(Constant.WAIT_TIME);
                    while (queue.size() == maxSize) {
                        System.out.println("队列已满,生产者--[" + this.getName() + "]进行等待");
                        queue.wait();
                    }
                    int num = ThreadLocalRandom.current().nextInt() * 100;
                    queue.offer(num);
                    System.out.println("[" + this.getName() + "]生产一个元素:" + num);
                    queue.notifyAll();
                    System.out.println("当前队列中有[" + queue.size() + "]个元素");
                } catch (InterruptedException e) {
                    System.out.println("Error info 1...");
                }
            }
        }
    }
}
