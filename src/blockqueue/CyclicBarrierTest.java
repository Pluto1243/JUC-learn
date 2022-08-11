package blockqueue;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * TODO 十个人排队 一起用餐
 *
 * @author wangjie
 * @date 22:16 2022年04月25日
 **/
public class CyclicBarrierTest {

  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
    Thread t1 = new Thread(new People1("A", cyclicBarrier));
    Thread t2 = new Thread(new People1("B", cyclicBarrier));
    Thread t3 = new Thread(new People1("C", cyclicBarrier));
    Thread t4 = new Thread(new People1("D", cyclicBarrier));
    Thread t5 = new Thread(new People1("E", cyclicBarrier));
    Thread t6 = new Thread(new People1("F", cyclicBarrier));
    Thread t7 = new Thread(new People1("H", cyclicBarrier));
    Thread t8 = new Thread(new People1("I", cyclicBarrier));
    Thread t9 = new Thread(new People1("J", cyclicBarrier));
    Thread t10 = new Thread(new People1("K", cyclicBarrier));
    long start = System.currentTimeMillis();
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    t6.start();
    t7.start();
    t8.start();
    t9.start();
    t10.start();
  }

}

class People1 implements Runnable {

  private String name;

  private CyclicBarrier cyclicBarrier;

  public People1(String name, CyclicBarrier cyclicBarrier) {
    this.name = name;
    this.cyclicBarrier = cyclicBarrier;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(3000);
      System.out.println(name + "到了！");
      cyclicBarrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
    System.out.println(name + "开始会议");
  }
}
