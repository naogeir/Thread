import java.util.Queue;

/**
 * @Author: noageir
 * @Date: 2019/3/21 16:10
 * Project: Thread
 * Package: PACKAGE_NAME
 */
public class Consumer extends Thread {
    private final Queue<Integer> queue;

    Consumer(String name, Queue<Integer> queue) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                try {
                    Thread.sleep(Constant.WAIT_TIME);
                    while (queue.isEmpty()) {
                        System.out.println("队列为空,消费者--[" + this.getName() + "]进行等待");
                        queue.wait();
                    }
                    int num = queue.poll();
                    System.out.println("[" + this.getName() + "]消费一个元素--[" + num + "]");
                    queue.notifyAll();
                } catch (InterruptedException e) {
                    System.out.println("Error info 2...");
                }
            }
        }
    }
}
