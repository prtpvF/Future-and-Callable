import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// можем получать значения от потоков
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService =  Executors.newFixedThreadPool(1);
        // Для возврата значений в потоке используем не анонимный класс Runnable а интерфейс Callable
        Future<Integer> future = executorService.submit(() -> {     // при использовании лямбды, можно озвращать значения, так как java понимает что используем интерфейс
            System.out.println("starting");                         // интерфейс Future используется для получения доступа к возвращаемому значению
            Thread.sleep(500);
            System.out.println("finished");
            Random random = new Random();
            int randomValue = random.nextInt();
            if (randomValue<5){
                throw new Exception("Something bad happened");
            }
            return random.nextInt(10);
        });


        executorService.shutdown();
        int result = future.get();  // get дожидается окончания выполнения потока
        System.out.println(result);
    }

}
