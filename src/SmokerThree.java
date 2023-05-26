public class SmokerThree implements Runnable{
    @Override public void run(){
        while (true){
            try {
                Main.lock.lock();
                while (!Main.paper && !Main.tobacco) {
                    try {
                        Main.smoker_three.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Smoker 3 is smoking...");
                Main.smoking_doing();
                Main.paper = false;
                Main.match = false;
                Main.tobacco = false;
                Main.bartender.signal();
            } finally {
                Main.lock.unlock();
            }
        }
    }
}

