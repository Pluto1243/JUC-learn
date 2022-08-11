import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程情况下，atomic与volatile相比较，能实现原子性
 *
 * @author wangjie
 * @date 0:08 2022年04月11日
 **/
public class AtomicTest {

  private static AtomicInteger num = new AtomicInteger();

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 100; i++) {
      new Thread(new Runnable() {

        @Override
        public void run() {
          for (int i = 0; i < 20000; i++) {
            // 相当于num++
            num.incrementAndGet();
          }
        }
      }).start();
    }
    //延迟3秒 确保100个线程创建完毕
    Thread.sleep(3000);
    System.out.println(num);
  }
}
