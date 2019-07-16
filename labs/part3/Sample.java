package part3;

import io.reactivex.Flowable;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    final List<String> symbols = List.of(
        "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
        "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

//    Flowable.<String>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
//        .map(StockPrice::getPrice)
//        .subscribe(System.out::println, System.out::println, () -> System.out.println("completed"));

    Flowable.interval(0, 1, TimeUnit.SECONDS)
        .map(index -> index.intValue() % symbols.size())
        .map(index -> symbols.get(index))
        .map(ticker -> ticker + " " + StockPrice.getPrice(ticker))
        .subscribe(System.out::println, System.out::println, () -> System.out.println("completed"));

    Thread.sleep(30000);

  }
}