package part4.labb;

import drone.DroneLocator;
import io.reactivex.Flowable;

import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Disposable disposable =
            DroneLocator.fetch("DR01")
                .skipWhile(data -> data.getAltitude() < 50)
                .take(25)
                .subscribe(
                    System.out::println,
                    System.out::println,
                    () -> System.out.println("Drone was landed"));

    }
}