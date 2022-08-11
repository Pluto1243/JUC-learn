/**
 * 多线程情况下，volatile关键词不能保证原子性
 *
 * @author wangjie
 * @date 0:01 2022年04月11日
 **/
public class VolatileTest {

  private static volatile int num = 0;

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 100; i++) {
      new Thread(new Runnable() {

        @Override
        public void run() {
          for (int i = 0; i < 20000; i++) {
            // num++ 不是原子性操作
            num++;
          }
        }
      }).start();
    }
    //延迟3秒 确保100个线程创建完毕
    Thread.sleep(3000);
    System.out.println(num);
  }
}
