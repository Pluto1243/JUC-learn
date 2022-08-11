package lockCar;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadTest {
  public static void main(String[] args) {
    BlockingQueue<CarData> blockingQueue =  new LinkedBlockingQueue<CarData>(100);
    CarStock stock = new CarStock(blockingQueue);
    CarProducer p1 = new CarProducer(stock);
    CarProducer p2 = new CarProducer(stock);
    CarConsumer c1 = new CarConsumer(stock);
    CarConsumer c2 = new CarConsumer(stock);
    // 加入线程池
    ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
    threadPoolExecutor.execute(p1);
    threadPoolExecutor.execute(p2);
    threadPoolExecutor.execute(c1);
    threadPoolExecutor.execute(c2);

  }
}
