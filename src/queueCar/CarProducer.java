package queueCar;

/**
 * 汽车生产者
 *
 * @author wangjie
 * @date 14:57 2022年04月12日
 **/
public class CarProducer implements Runnable{

  CarStock carStock;

  public CarProducer(CarStock carStock) {
    this.carStock = carStock;
  }

  @Override
  public void run() {
    while (true) {
      try {
        carStock.producerCar();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
