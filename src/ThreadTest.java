import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: noageir
 * @Date: 2019/3/21 17:52
 * Project: Thread
 * Package: PACKAGE_NAME
 */
public class ThreadTest {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 2;
        Producer producer = new Producer("Producer", queue, maxSize);
        Consumer consumerOne = new Consumer("consumerOne", queue);
        Consumer consumerTwo = new Consumer("consumerTwo", queue);
        Consumer consumerThree = new Consumer("consumerThree", queue);

        producer.start();
        consumerOne.start();
        consumerTwo.start();
        consumerThree.start();
    }


}
