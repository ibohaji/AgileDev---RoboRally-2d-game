package Utils;

public class Waiter {
    private static final Waiter instance = new Waiter();
    public static Waiter getInstance() {
        return instance;
    }
    public void waitForXMilliseconds(int ms){
        long startTime = System.currentTimeMillis();
        while(false||(System.currentTimeMillis()-startTime)<ms)
        {
            // do nothing for specified time
        }
    }
}
