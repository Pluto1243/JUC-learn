import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO 3个线程 依次打印123
 *
 * @author wangjie
 * @date 23:06 2022年04月13日
 **/
public class ThreadOutNum {
  Lock lock = new ReentrantLock();

  // 1线程的通知
  Condition c1 = lock.newCondition();
  // 2线程的通知
  Condition c2 = lock.newCondition();
  // 3线程的通知
  Condition c3 = lock.newCondition();

  public void print1() {
    lock.lock();
    try {
      System.out.println(1);
      Thread.sleep(1000);
      c2.signal();
      c1.await();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void print2() {
    lock.lock();
    try {
      System.out.println(2);
      Thread.sleep(1000);
      c3.signal();
      c2.await();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void print3() {
    lock.lock();
    try {
      System.out.println(3);
      Thread.sleep(1000);
      c1.signal();
      c3.await();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ThreadOutNum threadOutNum = new ThreadOutNum();
    ThreadOut1 threadOut1 = new ThreadOut1(threadOutNum);
    ThreadOut2 threadOut2 = new ThreadOut2(threadOutNum);
    ThreadOut3 threadOut3 = new ThreadOut3(threadOutNum);
    Thread t1 = new Thread(threadOut1);
    Thread t2 = new Thread(threadOut2);
    Thread t3 = new Thread(threadOut3);
    t1.start();
    Thread.sleep(1000);
    t2.start();
    Thread.sleep(1000);
    t3.start();
  }
}

class ThreadOut1 implements Runnable {

  ThreadOutNum threadOutNum;

  public ThreadOut1(ThreadOutNum threadOutNum) {
    this.threadOutNum = threadOutNum;
  }

  @Override
  public void run() {
    while (true) {
      threadOutNum.print1();
    }
  }
}

class ThreadOut2 implements Runnable {

  ThreadOutNum threadOutNum;

  public ThreadOut2(ThreadOutNum threadOutNum) {
    this.threadOutNum = threadOutNum;
  }

  @Override
  public void run() {
    while (true) {
      threadOutNum.print2();
    }
  }
}

class ThreadOut3 implements Runnable {

  ThreadOutNum threadOutNum;

  public ThreadOut3(ThreadOutNum threadOutNum) {
    this.threadOutNum = threadOutNum;
  }

  @Override
  public void run() {
    while (true) {
      threadOutNum.print3();
    }
  }
}
