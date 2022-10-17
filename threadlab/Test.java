
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ShareResourse shareResourse = new ShareResourse();
        Producter producter1 = new Producter(shareResourse);
        Producter producter2 = new Producter(shareResourse);
        Consumer consumer1 = new Consumer(shareResourse);
        Consumer consumer2 = new Consumer(shareResourse);
        Consumer consumer3 = new Consumer(shareResourse);
        Consumer consumer4 = new Consumer(shareResourse);
        Consumer consumer5 = new Consumer(shareResourse);
        
        Thread thread1 = new Thread(producter1, "厨师  1");
        Thread thread2 = new Thread(producter2, "厨师  2");
        Thread thread3 = new Thread(consumer1, "消费者1");
        Thread thread4 = new Thread(consumer2, "消费者2");
        Thread thread5 = new Thread(consumer3, "消费者3");
        Thread thread6 = new Thread(consumer4, "消费者4");
        Thread thread7 = new Thread(consumer5, "消费者5");
        
        thread1.start(); 
        thread2.start(); 
        thread3.start(); 
        thread4.start(); 
        thread5.start(); 
        thread6.start(); 
        thread7.start(); 
    }
}
