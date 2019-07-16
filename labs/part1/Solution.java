package part1;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Solution{

    public static void main(String[] args) {
        Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
            .map(data -> transform(data))
            .subscribe(data -> System.out.println(data), err -> {}, () -> System.out.println("no more data")) //comment out this line and try
        ;

        System.out.println("DONE");
    }

    public static int transform(int data) {
        System.out.println("transform called for " + data);
        return data * 2;
    }

    private static void emit(FlowableEmitter<Integer> emitter) throws InterruptedException
    {
        System.out.println("starting to emit....");
        int count = 0;

        while(count < 0) {
            emitter.onNext(count++); //this is the mouth of the data channel
            Thread.sleep(1000);
        }

        emitter.onComplete(); //this is the mout*h of the complete channel.
        //now the data channel is closed

    }
}