package queueCar;

/**
 * 汽车生产者
 *
 * @author wangjie
 * @date 14:57 2022年04月12日
 **/
public class CarConsumer implements Runnable{

  CarStock carStock;

  public CarConsumer(CarStock carStock) {
    this.carStock = carStock;
  }

  @Override
  public void run() {
    while (true) {
      try {
        carStock.consumeCar();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
