package chapter01;

import java.util.concurrent.TimeUnit;

/**
 * @author Rysiw
 * @date 2022/3/14 15:44
 */
public class TryConcurrency {

    public static void main(String[] args) {

        Thread thread = new Thread(TryConcurrency::browseNews);
        thread.start();
        thread.start();
        browseNews();
    }

    private static void enjoyMusic() {
        for (; ; ) {
            System.out.println("the good music");
            sleep(1);
        }
    }

    private static void browseNews() {
        for (; ; ) {
            System.out.println("good news");
            sleep(1);
        }
    }

    private static void sleep(int seconds) {

        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
