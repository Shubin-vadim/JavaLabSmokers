import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

  public static boolean tobacco = false;
  public static boolean paper = false;
  public static boolean match = false;

   public static Lock lock = new ReentrantLock();
    public final static Condition bartender = lock.newCondition();
   public final static Condition smoker_one = lock.newCondition();
   public final static Condition smoker_two = lock.newCondition();
    public final static Condition smoker_three = lock.newCondition();

    public static void main(String[] args) {

        SmokerOne smoker_one = new SmokerOne();
        SmokerTwo smoker_two = new SmokerTwo();
        SmokerThree smoker_three = new SmokerThree();
        Bartender bartender = new Bartender();

        Thread thread_smoker_one = new Thread(smoker_one);
        Thread thread_smoker_two = new Thread(smoker_two);
        Thread thread_smoker_three = new Thread(smoker_three);
        Thread thread_bartender = new Thread(bartender);

        thread_smoker_one.start();
        thread_smoker_two.start();
        thread_smoker_three.start();
        thread_bartender.start();
    }

    public static void smoking_doing(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}