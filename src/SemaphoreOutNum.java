import java.io.PrintWriter;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO 使用Semaphore实现线程通信依次打印123
 *
 * @author wangjie
 * @date 23:06 2022年04月13日
 **/
public class SemaphoreOutNum {
  //定义三个信号量，并且这三个信号量一共只有1个许可证
  Semaphore sema1 = new Semaphore(1);
  Semaphore sema2 = new Semaphore(0);
  Semaphore sema3 = new Semaphore(0);

  public static void main(String[] args) {
    SemaphoreOutNum semaphoreOutNum = new SemaphoreOutNum();
    Print123 p1 = new Print123(semaphoreOutNum);
    Print123 p2 = new Print123(semaphoreOutNum);
    Print123 p3 = new Print123(semaphoreOutNum);
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          p1.print("1",  semaphoreOutNum.sema1 , semaphoreOutNum.sema2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          p2.print("2",  semaphoreOutNum.sema2 , semaphoreOutNum.sema3);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    Thread t3 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          p3.print("3",  semaphoreOutNum.sema3 , semaphoreOutNum.sema1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    t1.start();
    t2.start();
    t3.start();
  }
}

class Print123 {
  SemaphoreOutNum semaphoreOutNum;
  public Print123(SemaphoreOutNum semaphoreOutNum) {
    this.semaphoreOutNum = semaphoreOutNum;
  }
  void print(String value, Semaphore cur, Semaphore next) throws InterruptedException {
    while (true) {
      // 该信号量获得许可
      cur.acquire();
      System.out.println(value);
      Thread.sleep(500);
      // 下一个信号量释放许可
      next.release();
    }
  }
}