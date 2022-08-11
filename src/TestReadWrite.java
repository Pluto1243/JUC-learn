import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试
 *
 * @author wangjie
 * @date 10:41 2022年04月17日
 **/
public class TestReadWrite {
  // 读写锁
  ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public static void main(String[] args) {
    TestReadWrite test = new TestReadWrite();

    new Thread(() -> {
      // 读操作
      test.testRead();
      // 写操作
      test.testWrite();
    }, "t1").start();


    new Thread(() -> {
      // 读操作
      test.testRead();
      // 写操作
      test.testWrite();
    }, "t2").start();
  }
  // 读锁
  void testRead() {
    lock.readLock().lock();
    try {
      for (int i = 0; i < 10000; i++) {
        System.out.println(Thread.currentThread() + " 正在读操作");
      }
      System.out.println(Thread.currentThread() + " 读操作完毕");
    } finally {
      lock.readLock().unlock();
    }
  }

  // 写锁
  void testWrite() {
    lock.writeLock().lock();
    try {
      for (int i = 0; i < 10000; i++) {
        System.out.println(Thread.currentThread() + " 正在写操作");
      }
      System.out.println(Thread.currentThread() + " 写操作完毕");
    } finally {
      lock.writeLock().unlock();
    }
  }
}
