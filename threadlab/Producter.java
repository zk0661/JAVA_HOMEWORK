import java.util.Random;

public class Producter implements Runnable {
    private ShareResourse shareResourse;
    Producter(ShareResourse shareResourse) {
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
                temp = random.nextInt(10) + 1;
                shareResourse.put(temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
