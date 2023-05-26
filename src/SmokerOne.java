public class SmokerOne implements Runnable{
    @Override public void run(){
       while (true){
           try {
               Main.lock.lock();
               while (!Main.paper && !Main.match) {
                try {
                    Main.smoker_one.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               }
               System.out.println("Smoker 1 is smoking...");
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
