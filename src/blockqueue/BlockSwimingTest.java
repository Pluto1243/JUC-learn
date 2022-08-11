package blockqueue;

import java.util.Calendar;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class BlockSwimingTest {
  public static void main(String[]args) throws InterruptedException {
    BlockingQueue<Swim> blockingQueue = new DelayQueue<>();
    blockingQueue.put(new Swim("小王", 1L));
    blockingQueue.put(new Swim("小祝", 2L));
    blockingQueue.put(new Swim("小邓", 3L));
    BlockSwiming blockSwiming = new BlockSwiming(blockingQueue);
    new Thread(blockSwiming).start();
  }
}

class BlockSwiming implements Runnable{
  BlockingQueue<Swim> blockingQueue;
  public BlockSwiming(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
  }


  @Override
  public void run() {
    while (true) {
      try {
        // 当剩余时间为0时取出元素
        Swim take = blockingQueue.take();
        System.out.println(take.getName() + "当前时间"+ Calendar.getInstance().getTime()+ " 游泳结束");
        if (blockingQueue.size() == 0) {
          System.out.println("线程结束");
          break;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Swim implements Delayed {
  private Long swimTime;
  private String name;

  public Swim (String name, Long swimTime) {
    this.name = name;
    this.swimTime = System.currentTimeMillis() + swimTime * 60 * 1000;
  }

  public Long getSwimTime() {
    return swimTime;
  }

  public void setSwimTime(Long swimTime) {
    this.swimTime = swimTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * @description 获取剩余时间，若为正数，则代表剩余的时间，若为负数，说明已超时，超时就会用take()取出元素
   *
   * @author wangjie
   * @date 11:02 2022年04月23日
   * @param unit
   * @return long
   */
  @Override
  public long getDelay(TimeUnit unit) {
    return swimTime - System.currentTimeMillis();
  }
  /**
   * @description 线程之间，根据剩余的时间排序
   *
   * @author wangjie
   * @date 11:03 2022年04月23日
   * @param o
   * @return int
   */
  @Override
  public int compareTo(Delayed o) {
    Swim swim = (Swim)o;
    return this.getDelay(TimeUnit.SECONDS) - swim.getDelay(TimeUnit.SECONDS) > 0?1 : 0;
  }
}