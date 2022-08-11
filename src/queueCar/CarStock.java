package queueCar;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO 使用BlockQueue实现
 *
 * @author wangjie
 * @date 09:59 2022年04月12日
 **/
public class CarStock {

  BlockingQueue<CarData> blockingQueue;

  public int count = 0;

  public Lock lock = new ReentrantLock();

  Condition condition = lock.newCondition();

  public CarStock(BlockingQueue<CarData> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  public void producerCar() throws InterruptedException {
    lock.lock();
      try {
        // 车辆数量小于100
        if (blockingQueue.size() < 100) {
          ++count;
        System.out.println(Thread.currentThread().getName() + "生产车" + count);
        CarData carData = new CarData();
        carData.setId(count);
        blockingQueue.put(carData);
        Thread.sleep(300);
        condition.signalAll();
        } else {
          condition.await();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
  }

  public void consumeCar() throws InterruptedException {
    lock.lock();
    try {
      // 车辆数量大于0
      if (blockingQueue.size() > 0) {
        CarData poll = blockingQueue.poll();
        --count;
        System.out.println(Thread.currentThread().getName() + "卖出车:"+count);
        Thread.sleep(300);
        condition.signalAll();
      } else {
        condition.await();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
