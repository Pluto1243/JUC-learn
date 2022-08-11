/**
 * TODO 生产车，两个生产者，两个消费者，一个car++，一个car--，生产者上产到上限，wait，消费者消费了再notify
 *
 * @author wangjie
 * @date 0:43 2022年04月11日
 **/
class CarFactory {

  int car = 0;

  public static void main(String[] args) {
    CarFactory carFactory = new CarFactory();
    CarProducer p1 = new CarProducer(carFactory);
    CarProducer p2 = new CarProducer(carFactory);
    CarConsumer c1 = new CarConsumer(carFactory);
    CarConsumer c2 = new CarConsumer(carFactory);
    Thread t1 = new Thread(p1);
    Thread t2 = new Thread(p2);
    Thread t3 = new Thread(c1);
    Thread t4 = new Thread(c2);
    t1.setName("生产车间1号");
    t2.setName("生产车间2号");
    t3.setName("消费车间1号");
    t4.setName("消费车间2号");
    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }
  synchronized void sellCar() throws InterruptedException {
    if (car > 0) {
      System.out.println(Thread.currentThread().getName() + "消费了一辆车， 还剩"+car);
      Thread.sleep(100);
      car--;
      notifyAll();
    } else {
      wait();
      System.out.println(Thread.currentThread().getName()+": 暂停了消费");
    }
  }

  synchronized void produceCar() throws InterruptedException {
    if (car < 20) {
      System.out.println(Thread.currentThread().getName() + "生产了一辆车， 还剩"+car);
      Thread.sleep(100);
      car++;
      notifyAll();
    } else {
      wait();
      System.out.println(Thread.currentThread().getName()+": 暂停了生产");
    }
  }
}

class CarProducer implements Runnable{
  CarFactory carFactory;

  public CarProducer(CarFactory c) {
    this.carFactory = c;
  }
  @Override
  public void run() {
    while (true) {
      try {
        carFactory.produceCar();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class CarConsumer implements Runnable {
  CarFactory carFactory;

  public CarConsumer(CarFactory c) {
    this.carFactory = c;
  }
  @Override
  public void run() {
    while (true) {
      try {
        carFactory.sellCar();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}