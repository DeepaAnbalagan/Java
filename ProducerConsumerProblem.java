package Thread;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author deepa.a
 */
public class ProducerConsumerProblem {

    public static void main(String[] args) {
        Vector list = new Vector();
        int size = 4;
        Thread producer = new Thread(new Producer(list, size));
        Thread consumer = new Thread(new Consumer(list, size));
        producer.start();
        consumer.start();
    }
}

class Producer implements Runnable {
    private final Vector list;
    private final int size;

    Producer(Vector list, int size) {
        this.list = list;
        this.size = size;
    }

    public void run() {
        for (int i = 0; i < 7; i++) {
            System.out.println("Producer Produced " + i);
            produce(i);
        }

    }

    public void produce(int i) {
        synchronized (list) {


            while (list.size() == size) {
                try {
                    System.out.println("Queue is full " + Thread.currentThread().getName()
                            + " is waiting , size: " + list.size());

                    list.wait();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

            }

        }
        synchronized (list) {
            list.add(i);
            list.notifyAll();
        }

    }

}

class Consumer implements Runnable {
    private final Vector list;
    private final int size;

    Consumer(Vector list, int size) {
        this.list = list;
        this.size = size;
    }

    public void run() {
        while (true) {
            System.out.println("Item Consumed  " + consume());
            try {
                consume();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }


    }

    public int consume() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    System.out.println("Queue is empty " + Thread.currentThread().getName()
                            + " is waiting , size: " + list.size());
                    list.wait();

                } catch (Exception e) {
                    System.out.println(e);
                }

            }


        }

        synchronized (list) {
            list.notifyAll();
            return (int) list.remove(0);
        }

    }
}
