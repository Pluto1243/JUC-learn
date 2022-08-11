/**
 * 两个站点售票问题
 *
 * @author wangjie
 * @date 0:19 2022年04月11日
 **/
public class SellTickers implements Runnable {
  private int tickers = 100;

  @Override
  public void run() {
    while (true) {
        sellTicker();
    }
  }
  // 加上synchronized 保证方法线程安全
  private synchronized void sellTicker() {
    if (tickers > 0) {
      System.out.println(Thread.currentThread().getName() + "卖出了一张票，还剩"+tickers+"张票");
      tickers--;
    }
  }

  public static void main(String[] args) {
    SellTickers t = new SellTickers();
    Thread t1 = new Thread(t);
    Thread t2 = new Thread(t);
    t1.setName("站点1");
    t2.setName("站点2");
    t1.start();
    t2.start();
  }
}
