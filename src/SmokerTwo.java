public class SmokerTwo implements Runnable{
    @Override public void run(){
        while (true){
            try {
                Main.lock.lock();
                while (!Main.tobacco && !Main.match) {
                    try {
                        Main.smoker_two.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Smoker 2 is smoking...");
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

    private void smoking(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
