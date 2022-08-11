package blockqueue;

import java.util.concurrent.CountDownLatch;

/**
 * TODO 十个人排队 一起用餐
 *
 * @author wangjie
 * @date 22:16 2022年04月25日
 **/
public class CountDownLatchTest {

  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(10);
    Thread t1 = new Thread(new People("A", countDownLatch));
    Thread t2 = new Thread(new People("B", countDownLatch));
    Thread t3 = new Thread(new People("C", countDownLatch));
    Thread t4 = new Thread(new People("D", countDownLatch));
    Thread t5 = new Thread(new People("E", countDownLatch));
    Thread t6 = new Thread(new People("F", countDownLatch));
    Thread t7 = new Thread(new People("H", countDownLatch));
    Thread t8 = new Thread(new People("I", countDownLatch));
    Thread t9 = new Thread(new People("J", countDownLatch));
    Thread t10 = new Thread(new People("K", countDownLatch));
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
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println("所有人到齐，耗时：");
      System.out.println(System.currentTimeMillis() - start);
    }
  }

}

class People implements Runnable {

  private String name;

  private CountDownLatch countDownLatch;

  public People(String name, CountDownLatch countDownLatch) {
    this.name = name;
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(3000);
      System.out.println(name + "到了！");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      countDownLatch.countDown();
    }
  }
}
