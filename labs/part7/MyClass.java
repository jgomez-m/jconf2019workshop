package part7;

import drone.DroneLocation;
import drone.DroneLocator;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MyClass
{

    public static void main(String[] args) {
        DroneLocator.fetch("DR01")
            .subscribe(new Subscriber<DroneLocation>() {
                private Subscription subscription;

                @Override
                public void onSubscribe(Subscription subscription) {
                    this.subscription = subscription;
                    this.subscription.request(10);
                    System.out.println("On Subscribe");
                }

                @Override
                public void onNext(DroneLocation data) {
                    System.out.println("received " + data);
                    sleep(1000);
                    if(data.getAltitude() < 50){
                        System.out.println("Less than alt 50");
                        subscription.request(1);
                    }
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onComplete() {

                }
            });

        sleep(100000);
    }

    private static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
