import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发容器中的数组迭代
 *
 * @author wangjie
 * @date 17:23 2022年04月16日
 **/
public class TestJUCArrayList {
  public static void main(String[] args) {
    CopyOnWriteArrayList<String> cwa = new CopyOnWriteArrayList<>();
    cwa.add("wj");
    cwa.add("zrx");
    cwa.add("sun");
    Iterator<String> iterator = cwa.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      cwa.add("test");
    }
    System.out.println(cwa.size());
  }
}
