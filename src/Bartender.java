import java.util.Random;

public class Bartender implements Runnable{
    private final Random randomDigit = new Random();

    @Override public void run(){
        while (true){
            try {
                Main.lock.lock();
                while ((Main.paper && Main.match) || (Main.paper && Main.tobacco) || (Main.match && Main.tobacco)) {
                    try {
                        Main.bartender.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int tmp = randomDigit.nextInt(3);
                if (tmp == 0){
                    Main.tobacco = false;
                    Main.paper = true;
                    Main.match = true;
                    System.out.println("Bartender take paper and matches");
                    Main.smoking_doing();
                    Main.smoker_one.signal();
                }

                if (tmp == 1){
                    Main.tobacco = true;
                    Main.paper = false;
                    Main.match = true;
                    System.out.println("Bartender take tobacco and matches");
                    Main.smoking_doing();
                    Main.smoker_two.signal();
                }

                if (tmp == 2){
                    Main.tobacco = true;
                    Main.paper = true;
                    Main.match = false;
                    System.out.println("Bartender take paper and tobacco");
                    Main.smoking_doing();
                    Main.smoker_three.signal();
                }

            } finally {
                Main.lock.unlock();
            }
        }
    }
}
