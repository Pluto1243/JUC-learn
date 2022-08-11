package lockCar;

import java.util.concurrent.BlockingQueue;

/**
 * TODO 使用BlockQueue实现
 *
 * @author wangjie
 * @date 09:59 2022年04月12日
 **/
public class CarStock {

  BlockingQueue<CarData> blockingQueue;

  public int count = 0;

  public CarStock(BlockingQueue<CarData> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  public synchronized void producerCar() throws InterruptedException {
    // 车辆数量小于100
    if (blockingQueue.size() < 100) {
      ++count;
      System.out.println(Thread.currentThread().getName() + "生产车"+count);
      CarData carData = new CarData();
      carData.setId(count);
      blockingQueue.put(carData);
      Thread.sleep(300);
      notifyAll();
    } else {
      wait();
    }
  }

  public synchronized void consumeCar() throws InterruptedException {
    // 车辆数量大于0
    if (blockingQueue.size() > 0) {
      CarData poll = blockingQueue.poll();
      --count;
      System.out.println(Thread.currentThread().getName() + "卖出车:"+count);
      Thread.sleep(300);
      notifyAll();
    } else {
      wait();
    }
  }
}
