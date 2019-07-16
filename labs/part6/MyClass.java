package part6;

import drone.DroneLocation;
import drone.DroneLocator;
import io.reactivex.Flowable;

public class MyClass
{
    public static void main(String[] args) throws InterruptedException {
        Flowable<DroneLocation> flowable = DroneLocator.fetch("DR01");

        flowable.subscribe(data -> print("S1", data));

        Thread.sleep(5000);

        flowable.subscribe(data -> print("S2", data));

        Thread.sleep(10000);
    }

    public static void print(String msg, DroneLocation data) {
        System.out.println(msg + ":" + data);
    }
}
