import java.util.Iterator;
import java.util.Vector;

/**
 * 并发读写
 * 每次迭代（iter.next()）都会确保全局变量modCount和ITR类的expectedModCount一致
 * 不一致会抛出Exception in thread "main" java.util.ConcurrentModificationException
 * @author wangjie
 * @date 17:09 2022年04月16日
 **/
public class TestVector {
  public static void main(String[] args) {
    Vector<String> vector = new Vector<>();
    vector.add("wj");
    vector.add("zrx");
    vector.add("sun");
    Iterator<String> iterator = vector.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      // vector.add("test");
    }
  }
}
