import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

  private static final ConcurrentLinkedQueue<Byte> cache = new ConcurrentLinkedQueue<>();

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    Proxy proxy = new Proxy();
    Byte packet = new Byte("100");
    //по идее должна быть проверка что backend упал, и только в этом случае мы добавляем в кэш пакеты
    long start = System.currentTimeMillis();
    for (int i = 0; i < 1000; i++) {
      CompletableFuture.runAsync(() -> proxy.receive(packet), executorService);

    }
    System.out.println("time add operation:" + (System.currentTimeMillis() - start));
    //если backend поднялся то очищаем кэш
    proxy.getDataAndSend();

  }
}
