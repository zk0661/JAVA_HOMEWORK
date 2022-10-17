public class ShareResourse {
    private int foodNum = 30;
    public synchronized void put(int s) throws InterruptedException {
        if (canPut(s)) {
            this.foodNum += s;
            System.out.println(Thread.currentThread().getName() + "生产了" + s + "个食物," + "当前食物总数为：" + getFoodNum());
            notify();
        }
        else {
            wait();
        }
    }
    public synchronized void get(int s) throws InterruptedException {
        if (canGet(s)) {
            this.foodNum -= s;
            System.out.println(Thread.currentThread().getName() + "消费了" + s + "个食物," + "当前食物总数为：" + getFoodNum());
            notify();
        }
        else {
            wait();
        }
    }
    public boolean canPut(int s) {
        return (s + this.foodNum) < 30;
    }
    public boolean canGet(int s) {
        return (this.foodNum - s) > 0;
    }
    public int getFoodNum() {
        return foodNum;
    }
}
