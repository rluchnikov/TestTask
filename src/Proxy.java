import java.util.concurrent.ConcurrentLinkedQueue;


public class Proxy {

  private static  ConcurrentLinkedQueue<Byte> cache;


  public void receive(Byte data) {
    if (cache == null) {
      cache = new ConcurrentLinkedQueue<>();
    }
    cache.add(data);
  }

  public void getDataAndSend() {
    if (!cache.isEmpty()) {
      System.out.println("cache size before: " +cache.size());
      cache.forEach((s) -> {
        //если отправка выполнена удаляем пакет из очереди
        if (this.send(s)) {
          cache.remove(s);
        }
      });
      System.out.println("cache size after: " +cache.size());
    }
  }

    private boolean send(Byte date) {
    //здесь должна быть отправка на backend с проверкой что пакет доставлен
      return true;
    }
}

