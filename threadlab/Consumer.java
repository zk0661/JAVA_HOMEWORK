import java.util.Random;

public class Consumer implements Runnable {
    private ShareResourse shareResourse;
    Consumer(ShareResourse shareResourse) {
        this.shareResourse = shareResourse;
    }
    @Override
    public void run() {
        Random random = new Random();
        int temp;
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {
                temp = random.nextInt(3) + 1;
                shareResourse.get(temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
